package com.devsu.hackerearth.backend.client.model;

import javax.persistence.Entity;

import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.Getter;

@Entity
@Getter
@Setter
@SuperBuilder
public class Client extends Person {
	private String password;
	private boolean active;

	public Client(){}
}
