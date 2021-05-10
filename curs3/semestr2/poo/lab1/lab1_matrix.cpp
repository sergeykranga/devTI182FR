#include <iostream>
using namespace std;

class Matrix
{
    int **matrix, matrixRows, matrixColumns;

public:
    Matrix()
    {
        matrixRows = 0;
        matrixColumns = 0;
        matrix = new int *[matrixRows];
        for (int i = 0; i < matrixRows; ++i)
        {
            matrix[i] = new int[matrixColumns];
            for (int j = 0; j < matrixColumns; ++j)
                matrix[i][j] = 0;
        }
    }

    Matrix(int dimension)
    {
        matrixRows = dimension;
        matrixColumns = dimension;
        matrix = new int *[matrixRows];
        for (int i = 0; i < matrixRows; ++i)
        {
            matrix[i] = new int[matrixColumns];
            for (int j = 0; j < matrixColumns; ++j)
                matrix[i][j] = 0;
        }
    }

    Matrix(int rows, int columns)
    {
        matrixRows = rows;
        matrixColumns = columns;
        matrix = new int *[matrixRows];
        for (int i = 0; i < matrixRows; ++i)
        {
            matrix[i] = new int[matrixColumns];
            for (int j = 0; j < matrixColumns; ++j)
                matrix[i][j] = 0;
        }
    }

    void readFromConsole()
    {
        cout << "Enter Matrix elements: ";
        for (int i = 0; i < matrixRows; i++)
        {
            for (int j = 0; j < matrixColumns; j++)
            {
                cin >> matrix[i][j];
            }
        }
    }

    void show()
    {
        cout << "The Matrix is:\n";
        for (int i = 0; i < matrixRows; i++)
        {
            for (int j = 0; j < matrixColumns; j++)
            {
                cout << matrix[i][j] << " ";
            }
            cout << endl;
        }
    }

    Matrix &operator+(const Matrix &m1)
    {
        return (*this += m1);
    }

    Matrix &operator*(const Matrix &m1)
    {
        return (*this *= m1);
    }

    Matrix &operator+=(const Matrix &rhs)
    {
        for (int i = 0; i < matrixRows; i++)
        {
            for (int j = 0; j < matrixColumns; j++)
            {
                matrix[i][j] += rhs.matrix[i][j];
            }
        }
        return *this;
    }

    Matrix &operator*=(const Matrix &T)
    {
        if (matrixColumns == T.matrixRows)
        {
            for (int i = 0; i < T.matrixRows; ++i)
            {
                for (int k = 0; k < matrixColumns

                     ;
                     ++k)
                {
                    matrix[i][k] *= T.matrix[k][i];
                }
            }
        }

        return *this;
    }

    Matrix &operator=(const Matrix &T)
    {
        matrix = T.matrix;
        matrixColumns = T.matrixColumns;
        matrixRows = T.matrixRows;

        return *this;
    }
};

int main()
{
    Matrix firstMatrix(2, 2), secondMatrix(2, 2);

    firstMatrix.readFromConsole();
    cout << "\nfirstMatrix\n";
    firstMatrix.show();

    secondMatrix.readFromConsole();
    cout << "\nsecondMatrix\n";
    secondMatrix.show();

    cout << "\nsecondMatrix = secondMatrix + firstMatrix\n";
    secondMatrix = secondMatrix + firstMatrix;
    secondMatrix.show();

    cout << "\nsecondMatrix = (firstMatrix * secondMatrix)\n";
    secondMatrix = (firstMatrix * secondMatrix);
    secondMatrix.show();

    cout << "\nfirstMatrix += secondMatrix\n";
    firstMatrix += secondMatrix;
    firstMatrix.show();
}
