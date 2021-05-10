#include <iostream>

using namespace std;

template<class ElementType> void reorder(ElementType array[], int array_size);

template<class ElementType> void reorder(ElementType array[], int array_size)
{
    int start_first_half = array_size / 2;
    int end_first_half = array_size;
    int start_second_half = 0;
    int end_second_half = start_first_half;

    ElementType middleElement;
    ElementType *pointer = NULL;

    if (array_size % 2 > 0)
    {
        middleElement = array[array_size / 2];
        pointer = &middleElement;
        start_first_half = start_first_half + 1;
    }

    for (int i = start_first_half; i < end_first_half; i++)
    {
        cout << array[i] << endl;
    }

    if (pointer != NULL)
    {
        cout << *pointer << endl;
    }

    for (int i = start_second_half; i < end_second_half; i++)
    {
        cout << array[i] << endl;
    }
}

class MyClass
{
    private:
        int number;
    public:
        MyClass() {}

        MyClass(int number)
        {
            this->number = number;
        }

        friend ostream& operator<<(ostream& os, const MyClass& dt);
};

ostream& operator<<(ostream& os, const MyClass& my_class)
{
    os << my_class.number;
    return os;
}

int main(int argc, char const *argv[])
{
    MyClass myClasses[] = {MyClass(1), MyClass(2), MyClass(3), MyClass(4), MyClass(5), MyClass(6), MyClass(7)};

    reorder(myClasses, 7);
}
