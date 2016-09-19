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
      String deleteContactQuery = "DELETE FROM contact *;";
      con.createQuery(deleteEntriesQuery).executeUpdate();
      con.createQuery(deleteContactQuery).executeUpdate();
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

  @Test
  public void save_returnsTrueIfDescriptionsAreTheSame() {
    Entry myEntry = new Entry("", "", "");
    myEntry.save();
    assertTrue(Entry.all().get(0).equals(myEntry));
  }

  @Test
  public void all_returnsAllInstancesOfEntry_true() {
    Entry firstEntry = new Entry("", "", "");
    firstEntry.save();
    Entry secondEntry = new Entry("", "", "");
    secondEntry.save();
    assertEquals(true, Entry.all().contains(firstEntry));
    assertEquals(true, Entry.all().contains(secondEntry));
  }

  @Test
  public void getId_entriesInstantiateWithAnID_0() {
    Entry myEntry = new Entry("", "", "");
    myEntry.save();
    assertTrue(myEntry.getId() > 0);
  }

// @Test
// public void find_returnsEntryWithSameId_secondEntry() {
//   Entry firstEntry = new Entry("", "", "");
//   firstEntry.save();
//   Entry secondEntry = new Entry("", "", "");
//   secondEntry.save();
//   assertEquals(Entry.find(secondEntry.getId()), secondEntry);
// }

//   @Test
//   public void save_savesContactIdIntoDB_true() {
//     Contact myContact = new Contact("Household chores");
//     myContact.save();
//     Entry myEntry = new Entry("Mow the lawn", myContact.getId());
//     myEntry.save();
//     Entry savedEntry = Entry.find(myEntry.getId());
//     assertEquals(savedEntry.getContactId(), myContact.getId());
//   }
}
