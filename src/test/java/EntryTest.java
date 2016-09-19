import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;

public class EntryTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/addressbook_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteEntriesQuery = "DELETE FROM entry *;";
      // String deleteContactQuery = "DELETE FROM contact *;";
      con.createQuery(deleteEntriesQuery).executeUpdate();
      // con.createQuery(deleteContactQuery).executeUpdate();
    }
  }

  @Test
  public void Entry_instantiatesCorrectly_true() {
    Entry myEntry = new Entry("Phone", "Mailing", "String");
    assertEquals(true, myEntry instanceof Entry);
  }

  @Test
  public void Entry_instantiatesWithPhoneNumber_String() {
    Entry myEntry = new Entry("Phone", "Mailing", "String");
    assertEquals("Phone", myEntry.getPhoneNumber());
  }

  @Test
  public void Entry_instantiatesWithMailingAddress_String() {
    Entry myEntry = new Entry("Phone", "Mailing", "String");
    assertEquals("Mailing", myEntry.getMailingAddress());
  }

  @Test
  public void Entry_instantiatesWithEmailAddress_String() {
    Entry myEntry = new Entry("Phone", "Mailing", "Email");
    assertEquals("Email", myEntry.getEmailAddress());
  }

  // @Test
  // public void Entry_instantiatesWithID_int() {
  //   Entry myEntry = new Entry("Phone", "Mailing", "String");
  //   assertEquals(1, myEntry.getCategoryId());
  // }

  @Test
  public void save_assignsIdToObject() {
    Entry myEntry = new Entry("", "", "");
    myEntry.save();
    Entry savedEntry = Entry.all().get(0);
    assertEquals(myEntry.getId(), savedEntry.getId());
  }

    @Test
    public void equals_returnsTrueIfDescriptionsAretheSame() {
      Entry firstEntry = new Entry("", "", "");
      Entry secondEntry = new Entry("", "", "");
      assertTrue(firstEntry.equals(secondEntry));
    }
}

//

//
//   @Test
//   public void save_returnsTrueIfDescriptionsAreTheSame() {
//     Task myTask = new Task("mow the lawn", 1);
//     myTask.save();
//     assertTrue(Task.all().get(0).equals(myTask));
//   }
//
//   @Test
//   public void all_returnsAllInstancesOfTask_true() {
//     Task firstTask = new Task("Mow the lawn", 1);
//     firstTask.save();
//     Task secondTask = new Task("Buy groceries", 1);
//     secondTask.save();
//     assertEquals(true, Task.all().contains(firstTask));
//     assertEquals(true, Task.all().contains(secondTask));
//   }
//
//   @Test
//   public void getId_tasksInstantiateWithAnID_1() {
//     Task myTask = new Task("Mow the lawn", 1);
//     myTask.save();
//     assertTrue(myTask.getId() > 0);
//   }
//
//   @Test
//   public void find_returnsTaskWithSameId_secondTask() {
//     Task firstTask = new Task("Mow the lawn", 1);
//     firstTask.save();
//     Task secondTask = new Task("Buy groceries", 1);
//     secondTask.save();
//     assertEquals(Task.find(secondTask.getId()), secondTask);
//   }
//
//   @Test
//   public void save_savesCategoryIdIntoDB_true() {
//     Category myCategory = new Category("Household chores");
//     myCategory.save();
//     Task myTask = new Task("Mow the lawn", myCategory.getId());
//     myTask.save();
//     Task savedTask = Task.find(myTask.getId());
//     assertEquals(savedTask.getCategoryId(), myCategory.getId());
//   }
//
// }
