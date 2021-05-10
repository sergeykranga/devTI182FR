#include <iostream>

using namespace std;

class Igra
{
    private:
        string nazvanie;
        int kolvo_igrokov;
        int dlitelinosti;
    
    public:
        Igra(string nazvanie, int kolvo_igrokov, int dlitelinosti) {
            this->nazvanie = nazvanie;
            this->kolvo_igrokov = kolvo_igrokov;
            this->dlitelinosti = dlitelinosti;
        }

        void info_ob_igre() {
            cout << "Nazvanie: " << nazvanie << endl;
            cout << "Kolichestvo igrokov: " << kolvo_igrokov << endl;
            cout << "Dlitelinosti igry: " << dlitelinosti << endl;
        }
};

class SportivnaiaIgra : public Igra
{
    private:
        bool indoor;
        bool outdoor;
    
    public:
        SportivnaiaIgra(bool indoor, bool outdoor, string nazvanie, int kolvo_igrokov, int dlitelinosti)
        : Igra(nazvanie, kolvo_igrokov, dlitelinosti)
        {
            this->indoor = indoor;
            this->outdoor = outdoor;
        }

        void info_o_sportivnoi_igre() {
            cout << "Mojno igrati v pomeshenii: " << indoor << endl;
            cout << "Mojno igrati na ulitse: " << outdoor << endl;
        }
};

class Voleybol : public SportivnaiaIgra
{
    public:
        Voleybol()
        : SportivnaiaIgra(true, true, "Voleybol", 12, 60)
        {
        }
};

int main(int argc, char const *argv[])
{
    Voleybol v = Voleybol();
    v.info_ob_igre();
    v.info_o_sportivnoi_igre();
    
    Igra a = Igra("Chess", 2, 120);
    a.info_ob_igre();
}
