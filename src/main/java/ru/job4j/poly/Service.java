package ru.job4j.poly;

public class Service {
    private Store store;

    public Service(Store store) {
        this.store = store;
    }

    public void add() {
        store.save("Nikita Vozhegov");
    }

    public static void main(String[] args) {
        FileStore fileStore = new FileStore("\\C:\\");
        Service service = new Service(fileStore);
        service.add();
    }
}
