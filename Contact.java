public Contact queryContact(String name) {
        Integer index = contactIndex.get(name);
        if (index != null) {
            return myContacts.get(index);
        }
        return null;
    }

    //  Print contacts with better formatting
    public void printContacts() {
        System.out.println("\nContact List:");
        System.out.printf("%-5s %-20s %-15s%n", "No.", "Name", "Phone Number");
        System.out.println("----------------------------------------");

        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            System.out.printf("%-5d %-20s %-15s%n", (i + 1), contact.getName(), contact.getPhoneNumber());
        }
        System.out.println("----------------------------------------");
    }
}
