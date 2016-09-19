import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Entry {
  private String phone_number;
  private String mailing_address;
  private String email_address;
  private int id;
  // private int categoryId;

  public Entry(String phone_number, String mailing_address, String email_address) {
    this.phone_number = phone_number;
    this.mailing_address = mailing_address;
    this.email_address = email_address;
    // this.categoryId = categoryId;
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
  //
  // public int getCategoryId() {
  //   return categoryId;
  // }

  public static Entry find(int id) {
    String sql = "SELECT id FROM entry;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetchFirst(Entry.class);
    }
  }

  public void save() {
    try(Connection con= DB.sql2o.open()) {
      String sql = "INSERT INTO entry (phone_number, mailing_address, email_address) VALUES (:phone_number, :mailing_address, :email_address);";
      this.id = (int) con.createQuery(sql, true).addParameter("phone_number", this.phone_number).addParameter("mailing_address", this.mailing_address).addParameter("email_address", this.email_address).executeUpdate().getKey();
    }
  }

  public static List<Entry> all() {
    String sql = "SELECT phone_number, mailing_address, email_address, id FROM entry;";
    try(Connection con= DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Entry.class);
    }
  }

  @Override
  public boolean equals(Object otherEntry) {
    if (!(otherEntry instanceof Entry)) {
      return false;
    } else {
      Entry newEntry = (Entry) otherEntry;
      return this.getId() == newEntry.getId();
    }
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
