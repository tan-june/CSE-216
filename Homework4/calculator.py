class Calculator:
    def __init__(self, num_str= "Please enter a number: ", operation_str="Enter an orithmetic operator: ", continue_str="Would you like to continue? ", end_msg="Bye!"):
        self.num_str = num_str
        self.operation_str = operation_str
        self.continue_str = continue_str
        self.end_msg = end_msg
        self.operand1 = None
        self.operand2 = None
        self.operator = None

    def get_number(self, string):
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

    def get_operator(self, string):
        while True:
            operator = input(string).strip()
            if operator in ['/', '*','-', '+']:
                return operator
            else:
                print("You may only enter one of the following operators: + - * /")

    def halt(self, string):
        while True:
            response = input(string).strip()
            if response in ['Y', 'y', 'YES', 'Yes', 'yes']:
                return True
            elif response in ['N', 'n', 'NO', 'No', 'no']:
                return False
            print("Invalid response. Please enter [Y|N].")

    def calculate(self):
        self.operand1 = self.get_number(self.num_str)
        self.operator = self.get_operator(self.operation_str)
        self.operand2 = self.get_number(self.num_str)
        
        if self.operator == '+':
            result = self.operand1 + self.operand2
        elif self.operator == '-':
            result = self.operand1 - self.operand2
        elif self.operator == '*':
            result = self.operand1 * self.operand2
        elif self.operator == '/':
            if self.operand2 == 0:
                print("Divide by Zero Error")
                return None
            else:
                result = self.operand1 / self.operand2
        else:
            print("Invalid operation.")
            return None

        return float(result)

    def run(self):
        calculation_array = []
        count = 0
        while True:
            count = count + 1
            result = self.calculate()
            calculation_array.append(result)
            print(f"{int(self.operand1)} {self.operator} {int(self.operand2)} = {result}")
            if not self.halt(self.continue_str):
                break
        print(f"You carried out {count} calculations. The results were: {'; '.join(str(result) for result in calculation_array)}")
        if self.end_msg:
            print(self.end_msg)
