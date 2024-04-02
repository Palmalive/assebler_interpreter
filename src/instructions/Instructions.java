package instructions;

import exceptions.InstructionException;
import exceptions.LabelException;
import fiags.FlagsService;
import labels.LabelService;
import registers.RegisterService;
import registers.Registers;

public enum Instructions {
    MOV {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .value1(tokens[1])
                    .destination(tokens[0])
                    .build();

            RegisterService.put(valuesParser.destination, valuesParser.value1);
        }
    },
    ADD {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .value1(tokens[1])
                    .value2(tokens[2])
                    .destination(tokens[0])
                    .build();
            long result = valuesParser.value1 + valuesParser.value2;
            FlagsService.carryFlag = result > 0xffffffffL;
            RegisterService.put(valuesParser.destination, result);

            FlagsService.notMovBBeqBne(result);
        }
    },

    SUB {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .value1(tokens[1])
                    .value2(tokens[2])
                    .destination(tokens[0])
                    .build();

            FlagsService.carryFlag = valuesParser.value1 < valuesParser.value2;
            long result = valuesParser.value1 - valuesParser.value2;
            RegisterService.put(valuesParser.destination, result);

            FlagsService.notMovBBeqBne(result);
        }
    },
    MUL {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .value1(tokens[1])
                    .value2(tokens[2])
                    .destination(tokens[0])
                    .build();

            long result = valuesParser.value1 * valuesParser.value2;
            RegisterService.put(valuesParser.destination, result);

            FlagsService.notMovBBeqBne(result);
        }
    },
    AND {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .value1(tokens[1])
                    .value2(tokens[2])
                    .destination(tokens[0])
                    .build();

            long result = valuesParser.value1 & valuesParser.value2;
            RegisterService.put(valuesParser.destination, result);

            FlagsService.notMovBBeqBne(result);
        }
    },
    ORR {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .value1(tokens[1])
                    .value2(tokens[2])
                    .destination(tokens[0])
                    .build();

            long result = valuesParser.value1 | valuesParser.value2;
            RegisterService.put(valuesParser.destination, result);

            FlagsService.notMovBBeqBne(result);
        }
    },


    ADC {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .value1(tokens[1])
                    .value2(tokens[2])
                    .destination(tokens[0])
                    .build();

            long result = valuesParser.value1 + valuesParser.value2 + (FlagsService.carryFlag ? 1 : 0);
            RegisterService.put(valuesParser.destination, result);

            FlagsService.notMovBBeqBne(result);
        }
    },
    CMP {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .value1(tokens[0])
                    .value2(tokens[1])
                    .build();

            long result = valuesParser.value1 - valuesParser.value2;

            FlagsService.carryFlag = valuesParser.value1 < valuesParser.value2;

            FlagsService.notMovBBeqBne(result);
        }
    },
    B {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .labelAddress(tokens[0])
                    .build();

            InstructionExecutor.instructionPointer = valuesParser.labelAddress-1;
        }
    },
    BEQ {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .labelAddress(tokens[0])
                    .build();

            if (FlagsService.zeroFlag) {
                InstructionExecutor.instructionPointer = valuesParser.labelAddress-1;
            }
        }
    },
    BNE {
        @Override
        public void execute(String arguments) throws InstructionException {
            String[] tokens = arguments.split(" ");
            ValuesParser valuesParser = new ValuesParser.Builder()
                    .labelAddress(tokens[0])
                    .build();

            if (!FlagsService.zeroFlag) {
                InstructionExecutor.instructionPointer = valuesParser.labelAddress-1;
            }
        }
    },;

    public abstract void execute(String arguments) throws InstructionException;

    private static long getValueFromRegister(String token) throws InstructionException {


        if (token.startsWith("0x") || token.startsWith("0b")) {
            switch (token.substring(0, 2)) {
                case "0x" -> {
                    token = token.substring(2);
                    token = String.valueOf(Long.parseLong(token, 16));
                }
                case "0b" -> {
                    token = token.substring(2);
                    token = String.valueOf(Long.parseLong(token, 2));
                }

            }
        }

        if (token.matches("[-+]?\\d+")) {
            return Long.parseLong(token);
        } else {
            try {
                return RegisterService.get(Registers.valueOf(token.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new InstructionException("There is no such register");
            }
        }
    }

    private static class ValuesParser {
        long value1;
        long value2;
        Registers destination;
        int labelAddress;

        public ValuesParser(Builder builder) {
            this.value1 = builder.value1;
            this.value2 = builder.value2;
            this.destination = builder.destination;
            this.labelAddress = builder.labelAddress;
        }

        public static class Builder {
            long value1;
            long value2;
            Registers destination;
            int labelAddress;

            public Builder value1(String value1) throws InstructionException {
                this.value1 = Instructions.getValueFromRegister(value1);
                return this;
            }

            public Builder value2(String value2) throws InstructionException {
                this.value2 = Instructions.getValueFromRegister(value2);
                return this;
            }

            public Builder destination(String destination) throws InstructionException {
                try {
                    this.destination = Registers.valueOf(destination.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InstructionException("There is no such register");
                }
                return this;
            }

            public Builder labelAddress(String labelAddress) throws InstructionException {

                try {
                    this.labelAddress = LabelService.getLabelAddress(labelAddress);
                } catch (LabelException e) {
                    throw new InstructionException(e.getMessage());
                }

                return this;
            }


            public ValuesParser build() {
                return new ValuesParser(this);
            }
        }
    }
}
