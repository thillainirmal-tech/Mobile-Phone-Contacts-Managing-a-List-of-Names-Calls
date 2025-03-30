import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;
    private Map<String, Integer> contactIndex;  // For faster lookups

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
        this.contactIndex = new HashMap<>();
    }

    //  Add new contact with improved validation
    public boolean addNewContact(Contact contact) {
        if (contact == null || contact.getName().isEmpty() || contact.getPhoneNumber().isEmpty()) {
            System.out.println("Invalid contact details.");
            return false;
        }

        if (contactIndex.containsKey(contact.getName())) {
            System.out.println("Contact with this name already exists.");
            return false;
        }

        myContacts.add(contact);
        contactIndex.put(contact.getName(), myContacts.size() - 1);
        System.out.println("Contact added: " + contact.getName() + " -> " + contact.getPhoneNumber());
        return true;
    }

    // Update contact efficiently
    public boolean updateContact(Contact oldContact, Contact newContact) {
        if (oldContact == null || newContact == null) {
            System.out.println("Invalid contact(s).");
            return false;
        }

        Integer oldIndex = contactIndex.get(oldContact.getName());
        if (oldIndex == null) {
            System.out.println(oldContact.getName() + " was not found.");
            return false;
        }

        if (contactIndex.containsKey(newContact.getName()) && !oldContact.getName().equals(newContact.getName())) {
            System.out.println("Contact with name " + newContact.getName() + " already exists.");
            return false;
        }

        myContacts.set(oldIndex, newContact);
        contactIndex.remove(oldContact.getName());
        contactIndex.put(newContact.getName(), oldIndex);

        System.out.println("Updated: " + oldContact.getName() + " → " + newContact.getName());
        return true;
    }

    // Remove contact
    public boolean removeContact(Contact contact) {
        if (contact == null) {
            System.out.println("Invalid contact.");
            return false;
        }

        Integer index = contactIndex.get(contact.getName());
        if (index == null) {
            System.out.println(contact.getName() + " was not found.");
            return false;
        }

        myContacts.remove((int) index);
        contactIndex.remove(contact.getName());

        // Update map indices
        for (int i = index; i < myContacts.size(); i++) {
            contactIndex.put(myContacts.get(i).getName(), i);
        }

        System.out.println(contact.getName() + " was deleted.");
        return true;
    }
