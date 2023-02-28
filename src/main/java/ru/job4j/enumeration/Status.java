package ru.job4j.enumeration;

public enum Status {
    ACCEPTED("Принят") {
        private String message = "Автомобиль принят в СТО";

        @Override
        public String getMessage() {
            return message;
        }
    },
    IN_WORK("В работе") {
        private String message = "Автомобиль в работе";

        @Override
        public String getMessage() {
            return message;
        }
    },
    WAITING("Ожидание") {
        private String message = "Автомобиль ожидает";

        @Override
        public String getMessage() {
            return message;
        }
    },
    FINISHED("Выполнено") {
        private String message = "Все работы выполнены";

        @Override
        public String getMessage() {
            return message;
        }
    };

    private String info;

    Status(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public abstract String getMessage();
}
