#include <iostream>
using namespace std;

template <class StackElementType>
class Stack
{
    StackElementType *arr;
    int top;
    int capacity;

public:
    Stack(int size);
    void push(StackElementType);
    StackElementType pop();
    StackElementType peek();
    int size();
    bool isEmpty();
    bool isFull();

    // destructor
    ~Stack()
    {
        delete[] arr;
    }
};

template <class StackElementType>
Stack<StackElementType>::Stack(int size)
{
    arr = new StackElementType[size];
    capacity = size;
    top = -1;
}

template <class StackElementType>
void Stack<StackElementType>::push(StackElementType stack_element)
{
    if (isFull())
    {
        cout << "Stack is full" << endl;
        exit(1);
    }

    cout << "Inserting " << stack_element << endl;
    arr[++top] = stack_element;
}

template <class StackElementType>
StackElementType Stack<StackElementType>::pop()
{
    if (isEmpty())
    {
        cout << "Stack is empty" << endl;
        exit(1);
    }

    cout << "Removing " << peek() << endl;

    return arr[top--];
}

template <class StackElementType>
StackElementType Stack<StackElementType>::peek()
{
    if (!isEmpty())
    {
        return arr[top];
    }
    else
    {
        exit(1);
    }
}

template <class StackElementType>
int Stack<StackElementType>::size()
{
    return top + 1;
}

template <class StackElementType>
bool Stack<StackElementType>::isEmpty()
{
    return top == -1;
}

template <class StackElementType>
bool Stack<StackElementType>::isFull()
{
    return top == capacity - 1;
}

int main()
{
    Stack<string> stack(3);

    stack.push("First element");
    stack.push("Second element");
    stack.push("Third element");

    stack.pop();
    stack.pop();

    stack.push("Fourth element");

    cout << "Top element is: " << stack.peek() << endl;
    cout << "Stack size is: " << stack.size() << endl;

    stack.pop();

    if (stack.isEmpty())
    {
        cout << "Stack is empty" << endl;
    }
    else
    {
        cout << "Stack is not empty" << endl;
    }

    stack.pop();

    if (stack.isEmpty())
    {
        cout << "Stack is empty" << endl;
    }
    else
    {
        cout << "Stack is not empty" << endl;
    }
}
