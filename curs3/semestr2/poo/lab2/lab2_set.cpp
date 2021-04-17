#include <iostream>
#include <set>

using namespace std;

class MySet
{
private:
    set <int> set;

public:
    MySet();
    MySet(std::set<int> set);
    ~MySet();
    void belongs(int);
    void add(int);
    void remove(int);
    void display();

    friend MySet operator+(const MySet &mySet1, const MySet &mySet2);
    friend MySet operator*(const MySet &mySet1, const MySet &mySet2);
    friend MySet operator-(const MySet &mySet1, const MySet &mySet2);

    void operator+=(int number)
    {
        add(number);
    }

    bool operator==(MySet otherSet)
    {
        return set == otherSet.set;
    }
};

MySet::~MySet()
{
}

MySet::MySet()
{
}

MySet::MySet(std::set<int> initialSet)
{
    set = initialSet;
}

MySet operator+(const MySet &mySet1, const MySet &mySet2)
{
    set<int> result(mySet1.set);
    result.insert(mySet2.set.begin(), mySet2.set.end());

    return MySet(result);
}

MySet operator*(const MySet &mySet1, const MySet &mySet2)
{
    set<int> result;

    std::set<int>::iterator it;

    for (it = mySet1.set.begin(); it != mySet1.set.end(); ++it) {
        if (mySet2.set.count(*it)) {
            result.insert(*it);
        }
    }

    return MySet(result);
}

MySet operator-(const MySet &mySet1, const MySet &mySet2)
{
    set<int> result;

    std::set<int>::iterator it;

    for (it = mySet1.set.begin(); it != mySet1.set.end(); ++it) {
        if (!mySet2.set.count(*it)) {
            result.insert(*it);
        }
    }

    return MySet(result);
}

void MySet::belongs(int number) {
    if (set.count(number)) {
        cout << "Number " << number << " belongs to the set" << endl;
    } else {
        cout << "Number " << number << " does not belong to the set" << endl;
    }
}

void MySet::add(int number) {
    set.insert(number);
}

void MySet::remove(int number) {
    set.erase(number);
}

void MySet::display() {
    std::set<int>::iterator it;

    for (it = set.begin(); it != set.end(); ++it) {
        cout << *it;
    }

    cout << endl;
}

int main(int argc, char const *argv[])
{
    

    // joining
    MySet mySet1 = MySet();
    mySet1.add(1);
    MySet mySet2 = MySet();
    mySet2.add(2);
    MySet joined = mySet1 + mySet2;
    joined.display();

    // subtract
    MySet mySet3 = MySet();
    mySet3.add(1);
    mySet3.add(2);
    MySet mySet4 = MySet();
    mySet4.add(2);
    MySet subtracted = mySet3 - mySet4;
    subtracted.display();

    // add to set
    MySet mySet5 = MySet();
    mySet5+=10;
    mySet5.display();

    // if set equals to another set
    MySet mySet6 = MySet();
    mySet6+=12;
    MySet mySet7 = MySet();
    mySet7+=12;

    if (mySet6 == mySet7) {
        cout << "sets are equal" << endl;
    } else {
        cout << "sets are not equal" << endl;
    }

    MySet mySet8 = MySet();
    mySet8+=12;
    MySet mySet9 = MySet();
    mySet9+=13;

    if (mySet8 == mySet9) {
        cout << "sets are equal" << endl;
    } else {
        cout << "sets are not equal" << endl;
    }

    // belongs
    MySet mySet10 = MySet();
    mySet10.add(1);
    mySet10.belongs(2);
    mySet10.belongs(1);
}
