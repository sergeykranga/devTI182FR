#include <iostream>

using namespace std;

class Figure 
{
    public:
        virtual void area()
        {
            cout << "Area of the figure" << endl;
        }
};

class Square: public Figure
{
    private:
        int dlina_storony;
    
    public:
        Square(int dlina_storony)
        {
            this->dlina_storony = dlina_storony;
        }

        void area()
        {
            cout << "Square area: " << dlina_storony * 4 << endl;
        }
};

class Circle: public Figure
{
    private:
        int radius;
        double pi = 3.1416;

    public:
        Circle(int radius)
        {
            this->radius = radius;
        }

        void area()
        {
            cout << "Circle area: " << pi * radius * radius << endl;
        }
};

// right angle triangle
class Triangle: public Figure
{
    private:
        double dlina_a;
        double dlina_b;

    public:
        Triangle(double dlina_a, double dlina_b)
        {
            this->dlina_a = dlina_a;
            this->dlina_b = dlina_b;
        }

        void area()
        {
            cout << "Right angle triangle area: " << (dlina_a * dlina_b) / 2 << endl;
        }
};

class Trapeze: public Figure
{
    private:
        double a, b, h;

    public:
        Trapeze(double a, double b, double h)
        {
            this->a = a;
            this->b = b;
            this->h = h;
        }

        void area()
        {
            cout << "Trapeze area: " << (a + b) * h / 2 << endl;
        }
};

int main(int argc, char const *argv[])
{
    Figure* f;

    Square s = Square(20);
    Circle c = Circle(4);
    Triangle triangle = Triangle(20, 40);
    Trapeze trapeze = Trapeze(60, 12, 5);

    f = &s;
    f->area();

    f = &c;
    f->area();

    f = &triangle;
    f->area();

    f = &trapeze;
    f->area();
}
