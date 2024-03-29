package instructions;

import registers.RegisterService;
import registers.Registers;

public enum Instructions {
    MOV {
        @Override
        public void execute(String arguments) {
            String[] tokens = arguments.split(" ");
            Registers destination = Registers.valueOf(tokens[0].toUpperCase());
            long value;

            if (tokens[1].matches("[-+]?\\d+")) {
                value = Long.parseLong(tokens[1]);
            } else {
                value = RegisterService.get(Registers.valueOf(tokens[1].toUpperCase()));
            }
            RegisterService.put(destination, value);
            System.out.println("mov " + arguments);
        }
    },
    ADD {
        @Override
        public void execute(String arguments) {
            String[] tokens = arguments.split(" ");
            Registers destination = Registers.valueOf(tokens[0].toUpperCase());
            long value1;
            long value2;

            if (tokens[1].matches("[-+]?\\d+")) {
                value1 = Long.parseLong(tokens[1]);
            } else {
                value1 = RegisterService.get(Registers.valueOf(tokens[1].toUpperCase()));
            }

            if (tokens[2].matches("[-+]?\\d+")) {
                value2 = Long.parseLong(tokens[2]);
            } else {
                value2 = RegisterService.get(Registers.valueOf(tokens[2].toUpperCase()));
            }
            RegisterService.put(destination, value1 + value2);
            System.out.println("add " + arguments);
        }
    },

    SUB {
        @Override
        public void execute(String arguments) {
            String[] tokens = arguments.split(" ");
            Registers destination = Registers.valueOf(tokens[0].toUpperCase());
            long value1;
            long value2;

            if (tokens[1].matches("[-+]?\\d+")) {
                value1 = Long.parseLong(tokens[1]);
            } else {
                value1 = RegisterService.get(Registers.valueOf(tokens[1].toUpperCase()));
            }

            if (tokens[2].matches("[-+]?\\d+")) {
                value2 = Long.parseLong(tokens[2]);
            } else {
                value2 = RegisterService.get(Registers.valueOf(tokens[2].toUpperCase()));
            }
            RegisterService.put(destination, value1 - value2);
            System.out.println("sub " + arguments);
        }
    },
    MUL {
        @Override
        public void execute(String arguments) {
            String[] tokens = arguments.split(" ");
            Registers destination = Registers.valueOf(tokens[0].toUpperCase());
            long value1;
            long value2;

            if (tokens[1].matches("[-+]?\\d+")) {
                value1 = Long.parseLong(tokens[1]);
            } else {
                value1 = RegisterService.get(Registers.valueOf(tokens[1].toUpperCase()));
            }

            if (tokens[2].matches("[-+]?\\d+")) {
                value2 = Long.parseLong(tokens[2]);
            } else {
                value2 = RegisterService.get(Registers.valueOf(tokens[2].toUpperCase()));
            }
            RegisterService.put(destination, value1 * value2);
            System.out.println("mul " + arguments);
        }
    },
    AND {
        @Override
        public void execute(String arguments) {
            String[] tokens = arguments.split(" ");
            Registers destination = Registers.valueOf(tokens[0].toUpperCase());
            long value1;
            long value2;

            if (tokens[1].matches("[-+]?\\d+")) {
                value1 = Long.parseLong(tokens[1]);
            } else {
                value1 = RegisterService.get(Registers.valueOf(tokens[1].toUpperCase()));
            }

            if (tokens[2].matches("[-+]?\\d+")) {
                value2 = Long.parseLong(tokens[2]);
            } else {
                value2 = RegisterService.get(Registers.valueOf(tokens[2].toUpperCase()));
            }
            RegisterService.put(destination, value1 & value2);
            System.out.println("and " + arguments);
        }
    },
    ORR {
        @Override
        public void execute(String arguments) {
            String[] tokens = arguments.split(" ");
            Registers destination = Registers.valueOf(tokens[0].toUpperCase());
            long value1;
            long value2;

            if (tokens[1].matches("[-+]?\\d+")) {
                value1 = Long.parseLong(tokens[1]);
            } else {
                value1 = RegisterService.get(Registers.valueOf(tokens[1].toUpperCase()));
            }

            if (tokens[2].matches("[-+]?\\d+")) {
                value2 = Long.parseLong(tokens[2]);
            } else {
                value2 = RegisterService.get(Registers.valueOf(tokens[2].toUpperCase()));
            }
            RegisterService.put(destination, value1 | value2);
            System.out.println("orr " + arguments);
        }
    };

    public abstract void execute(String arguments);




}
