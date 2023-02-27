package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book firstBook = new Book("Clean code", 150);
        Book secondBook = new Book("Thinking In Java", 800);
        Book thirdBook = new Book("Norton Guides", 170);
        Book fourBook = new Book("Programming the Z80", 325);
        Book[] books = new Book[4];
        books[0] = firstBook;
        books[1] = secondBook;
        books[2] = thirdBook;
        books[3] = fourBook;
        for (int i = 0; i < books.length; i++) {
            Book newBook = books[i];
            System.out.println(newBook.getName() + " has " + newBook.getNumberPages() + " pages");
        }
        Book tempBook = books[0];
        books[0] = books[3];
        books[3] = tempBook;
        System.out.println("Array after rearranging books");
        for (int i = 0; i < books.length; i++) {
            Book newBook = books[i];
            System.out.println(newBook.getName() + " has " + newBook.getNumberPages() + " pages");
        }
        System.out.println("Output only a book named Clean code");
        for (int i = 0; i < books.length; i++) {
            Book newBook = books[i];
            if (newBook.getName().equals("Clean code")) {
                System.out.println(newBook.getName() + " has " + newBook.getNumberPages() + " pages");
            }

        }
    }
}
