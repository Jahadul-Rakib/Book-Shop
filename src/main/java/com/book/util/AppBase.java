package com.book.util;

import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.Permission;
import com.book.entity.User;
import com.book.entity.UserPermission;
import com.book.repository.UserService;
import com.book.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

public abstract class AppBase {
    private Object object;
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserService userService;
    @Autowired
    UserPermissionService userPermissionService;
    public void setInstance(AppBase object) {
        this.object = object;
    }

    public void doAclCheck(String methodName, Class... args) throws Exception {
        Method method = object.getClass().getMethod(methodName, args);
        AclCheck aclCheck = method.getAnnotation(AclCheck.class);
        if (httpSession.getAttribute("username")!=null){
            List<UserPermission> userPermissions = userPermissionService.getPermissionByUsername(httpSession.getAttribute("username").toString());
            boolean hasPermission = false;
            for (UserPermission userPermission : userPermissions) {
                for (Permission permission : aclCheck.permissionNames()) {
                    if (userPermission.getPermission().equals(permission)) {
                        hasPermission = true;
                        break;
                    } else {
                        hasPermission = false;
                    }
                }
            }
            if (!hasPermission) {
                throw new Exception("Not Permitted!");
            }
        }
//        Method m=h.getClass().getMethod("sayHello");
//
//        MyAnnotation manno=m.getAnnotation(MyAnnotation.class);
    };
}
