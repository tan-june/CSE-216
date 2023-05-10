class Calculator:

    def __init__(self, num_str1="Enter first number: ", operation_str="Enter the operator: ",
                 num_str2="Enter second number: ", continue_str="Would you like to continue? ", end_msg="Bye!"):
        self.__num_str1 = num_str1
        self.__num_str2 = num_str2
        self.__operation_str = operation_str
        self.__continue_str = continue_str
        self.__end_msg = end_msg
        self.__operand1 = None
        self.__operand2 = None
        self.__operator = None

    @staticmethod
    def get_number(string):
        while True:
            try:
                arg_input = input(string)
                number = float(arg_input)
                if "." in arg_input:
                    print("Invalid input. Please enter an integer.")
                else:
                    return number
            except ValueError:
                print("Invalid input. Please enter an integer.")

    @staticmethod
    def get_operator(string):
        while True:
            operator = input(string).strip()
            if operator in ['/', '*', '-', '+']:
                return operator
            else:
                print("You may only enter one of the following operators: + - * /")

    @staticmethod
    def halt(string):
        while True:
            response = input(string).strip()
            if response in ['Y', 'y', 'YES', 'Yes', 'yes']:
                return True
            elif response in ['N', 'n', 'NO', 'No', 'no']:
                return False
            print("Invalid response. Please enter [Y|N].")

    @staticmethod
    def calculate(self):
        self.__operand1 = self.get_number(self.__num_str1)
        self.__operator = self.get_operator(self.__operation_str)
        self.__operand2 = self.get_number(self.__num_str2)

        if self.__operator == '+':
            result = self.__operand1 + self.__operand2
        elif self.__operator == '-':
            result = self.__operand1 - self.__operand2
        elif self.__operator == '*':
            result = self.__operand1 * self.__operand2
        elif self.__operator == '/':
            if self.__operand2 == 0:
                print("Divide by Zero Error")
                return None
            else:
                result = self.__operand1 / self.__operand2
        else:
            print("Invalid operation.")
            return None

        return float(result)

    def run(self):
        calculation_array = []
        count = 0
        while True:
            count = count + 1
            result = self.calculate(self)
            calculation_array.append(result)
            print(f"{int(self.__operand1)} {self.__operator} {int(self.__operand2)} = {result}")
            if not self.halt(self.__continue_str):
                break
        print(
            f"You carried out {count} calculations. The results were: {'; '.join(str(result) for result in calculation_array)}")
        if self.__end_msg:
            print(self.__end_msg)
