package com.example.demo;

import javax.persistence.*;
import javax.xml.crypto.Data;

@Entity
@Table
public class UserData {
@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column
private long id;

@Column(length = 20, nullable = false)
private String name;

@Temporal(TemporalType.TIMESTAMP)
private Data datetime;

@Column(nullable = false)
private String text;

public long getId() { return id; }
public void setId( long id) { this.id = id; }

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public Data getDateTime() { return datetime; }
public void setDateTime(Data datetime) { this.datetime = datetime; }

public String getText() { return text; }
public void setText(String text) { this.text = text; }
}
