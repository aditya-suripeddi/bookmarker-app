package com.dev.bookmarker.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GenerationType;

import java.time.Instant;


@Entity
@Table(name = "bookmarks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {



   @Id
   //
   //  sequence is a database feature / object like table
   //  for generating unique values (see reference)
   //
   //  when spring.jpa.hibernate.ddl-auto is set to update in application.properties
   //  you will notice the following query run by hibernate that creates a sequence
   //  in database
   //
   //
   //  Hibernate: create sequence bm_id_seq start with 1 increment by 50
   //
   //  also refer: https://stackoverflow.com/questions/1649102/what-is-a-sequence-database-when-would-we-need-it/1649126#1649126
   //              https://stackoverflow.com/questions/1649102/what-is-a-sequence-database-when-would-we-need-it/1649126#1649126

   @SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bm_id_seq_gen")
   private Long id;

   @Column(nullable = false)
   private String title;

   @Column(nullable = false)
   private String url ;

   private Instant createdAt;

}