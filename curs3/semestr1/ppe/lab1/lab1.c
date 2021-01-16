#include <windows.h> // include Windows API

// define functions
LRESULT CALLBACK WndProc(HWND me, UINT sig, WPARAM wParam, LPARAM lParam);
#define WIN_HANDLED   ((LRESULT)0)

// main entry point into windows applications
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, PTSTR szCmdLine, int iShowCmd)
{
    WNDCLASSEX wnd; // instance of the Window class ('wnd' object)
    HWND hwnd;  // "handle" to the window object
    int status; // status of the processing to report back to Windows
    UNREFERENCED_PARAMETER(hPrevInstance);
    UNREFERENCED_PARAMETER(szCmdLine);

    // set attributes of the 'wnd' object
    wnd.cbSize = sizeof(wnd);
    wnd.style = CS_HREDRAW | CS_VREDRAW;
    wnd.cbClsExtra = 0;
    wnd.cbWndExtra = 0;
    wnd.hInstance = hInstance;
    wnd.hIcon = NULL; // LoadIcon(NULL, IDI_APPLICATION);
    wnd.hCursor = LoadCursor(NULL, IDC_ARROW);
    wnd.hbrBackground = GetStockObject(WHITE_BRUSH);
    wnd.lpszMenuName = NULL;
    wnd.hIconSm = NULL;
    wnd.lpszClassName = "HelloWin";
    wnd.lpfnWndProc = &WndProc; // attach the "window proc"

    // register the Window Class with Windows
    RegisterClassEx(&wnd);

    // constructor of the 'wnd' class
    hwnd = CreateWindow(
        wnd.lpszClassName,    // window class name
        "PPE LAB1",    // window caption
        WS_OVERLAPPEDWINDOW,  // window style
        CW_USEDEFAULT,        // initial x position
        CW_USEDEFAULT,        // initial y position
        300,                  // initial x size
        200,                  // initial y size
        NULL,                 // parent window handle
        NULL,                 // window menu handle
        hInstance,            // program instance handle
        NULL);                // creation parameters

    ShowWindow(hwnd, iShowCmd);
    UpdateWindow(hwnd);

    while (1) {
        MSG msg; // message object to receive

        status = GetMessage(&msg, NULL, 0, 0);

        if (status == 0) { // message NOT to be processed?
            status = msg.wParam;
            break; // terminate the event loop
        }

        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    return status; // return to Windows with the status of processing
}

// main event processing function
LRESULT CALLBACK WndProc(HWND me, UINT sig, WPARAM wParam, LPARAM lParam)
{
    LRESULT status; // status of the processing to report back to Windows

    switch (sig) {       // signal of the message
        case WM_PAINT: { // window needs to be repainted
            PAINTSTRUCT ps;
            HDC hdc;
            RECT rect;
            char cBuffer[100];

            wsprintf(cBuffer, "Always centered client text");

            // painting performed between BeginPain() and EndPaint()...
            hdc = BeginPaint(me, &ps);

            GetClientRect(me, &rect);
            DrawText(hdc, cBuffer, -1, &rect, DT_SINGLELINE | DT_CENTER | DT_VCENTER);

            EndPaint(me, &ps);

            status = WIN_HANDLED; // report event handled
            break;
        }

        case WM_CREATE: { // window was created
            status = WIN_HANDLED; // report event handled
            break;
        }

        case WM_DESTROY: { // windows is about to be destroyed
            PostQuitMessage(0);
            status = WIN_HANDLED; // report event handled
            break;
        }

        default: { // default behavior (characteristic "look and feel")
            status = DefWindowProc(me, sig, wParam, lParam);
            break;
        }

    }

    return status; // return to Windows with the status of processing
}