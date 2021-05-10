#include <iostream>
#include <set>

using namespace std;

class Koleso
{
    private:
        int nomer_kolesa;
        int radius;
    
    public:
        Koleso(int radius, int nomer_kolesa)
        {
            this->radius = radius;
            this->nomer_kolesa = nomer_kolesa;
        }

        void radius_kolesa() {
            cout << "Radius kolesa " << nomer_kolesa << ": " << radius << endl;
        }

        bool operator<(const Koleso& koleso) const
        {
            return nomer_kolesa < koleso.nomer_kolesa;
        }
};

class Avtomobili
{
    private:
        set<Koleso> kolesa;
        string proizvoditeli;
    public:
        Avtomobili(int kolvo_koles, int radius_koleos, string proizvoditeli)
        {
            for (int i = 0; i < kolvo_koles; i++)
            {
                kolesa.insert(Koleso(radius_koleos, i));
            }

            this->proizvoditeli = proizvoditeli;
        }

        void info_ob_avtomobile()
        {
            cout << "Kolichestvo koleos: " << kolesa.size() << endl;
            std::set<Koleso>::iterator it;

            for (it = kolesa.begin(); it != kolesa.end(); ++it) {
                Koleso koleso = *it;
                koleso.radius_kolesa();
            }

            cout << "Proizvoditeli: " << proizvoditeli << endl;
        }
};

class GruzovoiAvtomobili: public Avtomobili
{
    public:
        GruzovoiAvtomobili(string proizvoditeli)
        : Avtomobili(6, 20, proizvoditeli)
        {
        }
};

class LegkovoiAvtomobili: public Avtomobili
{
    public:
        LegkovoiAvtomobili(string proizvoditeli)
        : Avtomobili(2, 17, proizvoditeli)
        {
        }
};

int main(int argc, char const *argv[])
{
    Avtomobili l = LegkovoiAvtomobili("Toyota");
    l.info_ob_avtomobile();
    Avtomobili g = GruzovoiAvtomobili("MAN");
    g.info_ob_avtomobile();
}
