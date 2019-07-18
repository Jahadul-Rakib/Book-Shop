package com.book.entity;

import org.springframework.security.core.GrantedAuthority;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "role_uuid")
	private Long roleId;
	@Column(unique = true, nullable = false)
	private String name;
	@OneToMany(orphanRemoval=true, fetch=FetchType.LAZY)
	List<User> users = new ArrayList<>();
    public Role () {
    }
	public Long getRoleId() {
		return roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String getAuthority() {
        return this.name;
    }
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
