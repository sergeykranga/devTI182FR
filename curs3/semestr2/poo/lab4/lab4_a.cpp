#include <iostream>

using namespace std;

class Worker 
{
    public:
        virtual void nachisiliti_zarplatu(int zarplata)
        {
            cout << "Nachisleno zarplaty: " << zarplata << endl;
        }
};

class StateWorker: public Worker
{
    private:
        int ejemeseachnaia_zarplata;
    
    public:
        StateWorker(int ejemeseachnaia_zarplata)
        {
            this->ejemeseachnaia_zarplata = ejemeseachnaia_zarplata;
        }

        void nachisiliti_zarplatu(int za_kolvo_meseatsev)
        {
            cout << "Nachisleno zarplaty za " << za_kolvo_meseatsev << " meseatsev: " << ejemeseachnaia_zarplata * za_kolvo_meseatsev << endl;
        }
};

class HourlyWorker: public Worker
{
    private:
        int chasov_otrabotano;

    public:
        HourlyWorker(int chasov_otrabotano)
        {
            this->chasov_otrabotano = chasov_otrabotano;
        }

        void nachisiliti_zarplatu(int zarplata_za_chas)
        {
            cout << "Nachisleno zarplaty za " << chasov_otrabotano << " chasov: " << zarplata_za_chas * chasov_otrabotano << endl;
        }
};

class CommissionWorker: public Worker
{
    private:
        int protsentnaia_stavka;

    public:
        CommissionWorker(int protsentnaia_stavka)
        {
            this->protsentnaia_stavka = protsentnaia_stavka;
        }

        void nachisiliti_zarplatu(int vsego_prodaj)
        {
            cout << "Nachisleno protsentnaia stavka " << protsentnaia_stavka << " ot obshih prodaj " << vsego_prodaj << ": " << vsego_prodaj * (protsentnaia_stavka * 0.01) << endl;
        }
};

int main(int argc, char const *argv[])
{
    Worker* w;

    StateWorker s = StateWorker(8000);
    HourlyWorker h = HourlyWorker(4);
    CommissionWorker c = CommissionWorker(20);

    w = &s;
    w->nachisiliti_zarplatu(12);

    w = &h;
    w->nachisiliti_zarplatu(100);

    w = &c;
    w->nachisiliti_zarplatu(8000);
}
