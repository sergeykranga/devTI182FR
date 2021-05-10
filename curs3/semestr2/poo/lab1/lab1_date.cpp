#include <iostream>
#include <string>

using namespace std;

class Date
{
private:
    int day, month, year;

public:
    Date();
    Date(int, int, int);
    ~Date();
    void setDay(int);
    void setMonth(int);
    void setYear(int);
    int getDay();
    int getMonth();
    int getYear();
    void showDateOption1();
    void showDateOption2();
};

Date::Date(/* args */)
{
    day = 1;
    month = 1;
    year = 1;
}

Date::Date(int day, int month, int year)
{
    setDay(day);
    setMonth(month);
    setYear(year);
}

Date::~Date()
{
}

void Date::setDay(int day)
{
    if (day < 1 || day > 31)
    {
        cout << "Day is invalid" << endl;
    }

    this->day = day;
}

void Date::setMonth(int month)
{
    if (month < 1 || month > 12)
    {
        cout << "Month is invalid" << endl;
    }

    this->month = month;
}

void Date::setYear(int year)
{
    if (year < 0)
    {
        cout << "Year is invalid" << endl;
    }

    this->year = year;
}

int Date::getDay()
{
    return this->day;
}

int Date::getMonth()
{
    return this->month;
}

int Date::getYear()
{
    return this->year;
}

void Date::showDateOption1()
{
    cout << day << "." << month << "." << year << endl;
}

void Date::showDateOption2()
{
    string monthName[] = {"January", "February", "March",
                          "April", "May", "June", "July",
                          "August", "September", "October",
                          "November", "December"};
    cout << day << " " << monthName[month - 1] << " " << year << endl;
}

int main()
{
    int day, month, year;

    cout << "Enter a day (between 1 - 31):" << endl;
    cin >> day;

    cout << "Please enter a month (between 1 - 12):" << endl;
    cin >> month;

    cout << "Please enter a year (non-negative):" << endl;
    cin >> year;

    Date date = Date(day, month, year);
    date.showDateOption1();
    date.showDateOption2();
}
