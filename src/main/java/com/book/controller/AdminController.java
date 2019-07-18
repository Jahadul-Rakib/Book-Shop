package com.book.controller;
import com.book.config.security.permission.AclCheck;
import com.book.config.security.permission.Permission;
import com.book.entity.Book;
import com.book.impl.UserSecurityService;
import com.book.repository.BookServiceRepo;
import com.book.repository.UserService;
import com.book.util.AppBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin")
public class AdminController extends AppBase {

	@Autowired
	UserService userService;
	@Autowired
	UserSecurityService userSecurityService;
	@Autowired
	BookServiceRepo repo;

	public AdminController(){
		super.setInstance(this);
	}
	@RequestMapping("/")
	public String login() {
		return "admin/login";
	}

	//@AclCheck(permissionNames = {Permission.ADMIN_ONLY, Permission.VIEW_BOOKS})
	@RequestMapping("/dashboard")
	public String logIn() throws Exception {
        //super.doAclCheck("logIn");
		return "admin/index";
	}
	@RequestMapping("/bookInfo")
	public String getBookDetails(@RequestParam("id") Long id, Model model){
		Book book = repo.fineById(id);
		model.addAttribute("book", book);
		return "admin/booksInfo";
	}

	@RequestMapping("/updateBook")
	public String doEdit(@RequestParam("id") Long id, Model model){
	    Book book = repo.fineById(id);
	    model.addAttribute("book",book);
		return "admin/updateBook";
	}

    @RequestMapping(value = "/updateBook", method =RequestMethod.POST)
    public String updateBookInfo(HttpServletRequest httpServletRequest, @ModelAttribute("book") Book book){

		Book book1 = repo.save(book);

		MultipartFile bookImage = book.getBookImage();
		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			Files.delete(Paths.get("src/main/resources/static/image/book/"+ name));
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/bookInfo?id="+book1.getId();
    }
}
