#include <iostream>

using namespace std;

class MyNumber
{
private:
    int number;

public:
    MyNumber();
    MyNumber(int);
    ~MyNumber();
    void display();
    friend MyNumber operator--(MyNumber &number, int);
    friend MyNumber operator-(const MyNumber &number1, const MyNumber &number2);
    friend MyNumber operator-(const int number1, const MyNumber &number2);
    friend MyNumber operator-(const MyNumber &number1, const int number2);

    MyNumber operator++(int)
    {
        number++;
        return *this;
    }

    MyNumber operator+(MyNumber otherNumber)
    {
        return MyNumber(number + otherNumber.number);
    }

    MyNumber operator+(int otherSimpleNumber)
    {
        return MyNumber(number + otherSimpleNumber);
    }
};

MyNumber::~MyNumber()
{
}

MyNumber::MyNumber(int num)
{
    number = num;
}

void MyNumber::display()
{
    cout << number << endl;
}

MyNumber operator--(MyNumber &number, int i)
{
    number.number--;
    return number;
}

MyNumber operator-(const MyNumber &myNumber1, const MyNumber &myNumber2)
{
    return MyNumber(myNumber1.number - myNumber2.number);
}

MyNumber operator-(const int myNumber1, const MyNumber &myNumber2)
{
    return MyNumber(myNumber1 - myNumber2.number);
}

MyNumber operator-(const MyNumber &myNumber1, const int myNumber2)
{
    return MyNumber(myNumber1.number - myNumber2);
}

int main(int argc, char const *argv[])
{
    MyNumber number = MyNumber(1);
    number++;
    number++;
    number++;
    number.display();

    MyNumber number1 = MyNumber(1);
    number1.display();
    MyNumber number2 = MyNumber(3);
    number2.display();
    MyNumber sum = number1 + number2;
    sum.display();

    MyNumber sumWithNumber = number1 + 10;
    sumWithNumber.display();

    MyNumber decremented = MyNumber(10);
    decremented--;
    decremented.display();

    MyNumber diff = number1 - number2;
    diff.display();

    MyNumber diffWithNumber = number1 - 1;
    diffWithNumber.display();

    MyNumber diffWithNumber2 = 2 - number1;
    diffWithNumber2.display();

    return 0;
}
