import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Entry {
  private String name;
  private String phone_number;
  private String mailing_address;
  private String email_address;
  private int id;
  private int categoryId;

  public Entry(String name, String phone_number, String mailing_address, String email_address, int categoryId) {
    this.name = name;
    this.phone_number = phone_number;
    this.mailing_address = mailing_address;
    this.email_address = email_address;
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phone_number;
  }

  public String getMailingAddress() {
    return mailing_address;
  }

  public String getEmailAddress() {
    return email_address;
  }

  public int getId() {
    return id;
  }

  public int getCategoryId() {
    return categoryId;
  }

}

//
//   public static List<Task> all() {
//     String sql = "SELECT id, description, categoryId FROM tasks";
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery(sql).executeAndFetch(Task.class);
//     }
//  }

//   public static Task find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM tasks where id=:id";
//       Task task = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Task.class);
//       return task;
//     }
//  }
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO tasks(description, categoryId) VALUES (:description, :categoryId)";
//       this.id = (int) con.createQuery(sql, true).addParameter("description", this.description).addParameter("categoryId", this.categoryId).executeUpdate().getKey();
//     }
//   }
//
//   @Override
//   public boolean equals(Object otherTask) {
//     if (!(otherTask instanceof Task)) {
//       return false;
//     } else {
//       Task newTask = (Task) otherTask;
//       return this.getDescription().equals(newTask.getDescription()) && this.getId() == newTask.getId() && this.getCategoryId() == newTask.getCategoryId();
//     }
//   }
//
// }
