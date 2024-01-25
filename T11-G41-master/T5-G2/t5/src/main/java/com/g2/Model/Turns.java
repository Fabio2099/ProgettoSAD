/*package com.g2.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="turn")
public class Turns {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private boolean is_winner;
   private String scores;
   private LocalDateTime created_at;
   private LocalDateTime update_at;
   private LocalDateTime started_at;
   private LocalDateTime closed_at;
   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
      this.id = id;
   }
   public boolean isIs_winner() {
      return is_winner;
   }
   public void setIs_winner(boolean is_winner) {
      this.is_winner = is_winner;
   }
   public String getScores() {
      return scores;
   }
   public void setScores(String scores) {
      this.scores = scores;
   }
   public LocalDateTime getCreated_at() {
      return created_at;
   }
   public void setCreated_at(LocalDateTime created_at) {
      this.created_at = created_at;
   }
   public LocalDateTime getUpdate_at() {
      return update_at;
   }
   public void setUpdate_at(LocalDateTime update_at) {
      this.update_at = update_at;
   }
   public LocalDateTime getStarted_at() {
      return started_at;
   }
   public void setStarted_at(LocalDateTime started_at) {
      this.started_at = started_at;
   }
   public LocalDateTime getClosed_at() {
      return closed_at;
   }
   public void setClosed_at(LocalDateTime closed_at) {
      this.closed_at = closed_at;
   }
   @Override
   public String toString() {
      return "Turns [id=" + id + ", is_winner=" + is_winner + ", scores=" + scores + ", created_at=" + created_at
            + ", update_at=" + update_at + ", started_at=" + started_at + ", closed_at=" + closed_at + "]";
   }
   

}*/

