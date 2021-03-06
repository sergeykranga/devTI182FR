#include <windows.h>
#include <time.h>

RECT rcCurrent = { 0,0,40,40 };
POINT aptStar[6] = { 20,2, 2,38, 38,12, 2,12, 38,38, 20,2 };
int X = 2, Y = -1, idTimer = -1;
BOOL fVisible = FALSE;
HDC hdc;

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
    wnd.lpszClassName = "PPE LAB2";
    wnd.lpfnWndProc = &WndProc; // attach the "window proc"

    // register the Window Class with Windows
    RegisterClassEx(&wnd);

    // constructor of the 'wnd' class
    hwnd = CreateWindow(
        wnd.lpszClassName,    // window class name
        "PPE LAB2",    // window caption
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

LRESULT CALLBACK WndProc(HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam)
{
    PAINTSTRUCT ps;
    RECT rc;

    switch (message)
    {
    case WM_CREATE:

        // Calculate the starting point.  

        GetClientRect(hwnd, &rc);
        OffsetRect(&rcCurrent, rc.right / 2, rc.bottom / 2);

        // Initialize the private DC.  

        hdc = GetDC(hwnd);
        SetViewportOrgEx(hdc, rcCurrent.left,
            rcCurrent.top, NULL);
        SetROP2(hdc, R2_NOT);

        // Start the timer.  

        SetTimer(hwnd, idTimer = 1, 10, NULL);
        return 0L;

    case WM_PAINT:

        // Show the star if it is not visible. Use BeginPaint  
        // to clear the update region.  

        BeginPaint(hwnd, &ps);
        if (!fVisible)
            fVisible = Polyline(hdc, aptStar, 6);
        EndPaint(hwnd, &ps);
        return 0L;

    case WM_SIZE:
        switch (wParam)
        {
        case SIZE_MINIMIZED:

            // Stop the timer if the window is minimized. 

            KillTimer(hwnd, 1);
            idTimer = -1;
            break;

        case SIZE_RESTORED:

            // Move the star back into the client area  
            // if necessary.  

            if (rcCurrent.right > (int)LOWORD(lParam))
            {
                rcCurrent.left =
                    (rcCurrent.right =
                        (int)LOWORD(lParam)) - 20;
            }
            if (rcCurrent.bottom > (int)HIWORD(lParam))
            {
                rcCurrent.top =
                    (rcCurrent.bottom =
                        (int)HIWORD(lParam)) - 20;
            }

            // Fall through to the next case.  

        case SIZE_MAXIMIZED:

            // Start the timer if it had been stopped.  

            if (idTimer == -1)
                SetTimer(hwnd, idTimer = 1, 10, NULL);
            break;
        }
        return 0L;

    case WM_TIMER:

        // Hide the star if it is visible.  

        if (fVisible)
            Polyline(hdc, aptStar, 6);

        // Bounce the star off a side if necessary.  

        GetClientRect(hwnd, &rc);
        if (rcCurrent.left + X < rc.left ||
            rcCurrent.right + X > rc.right)
            X = -X;
        if (rcCurrent.top + Y < rc.top ||
            rcCurrent.bottom + Y > rc.bottom)
            Y = -Y;

        // Show the star in its new position.  

        OffsetRect(&rcCurrent, X, Y);
        SetViewportOrgEx(hdc, rcCurrent.left,
            rcCurrent.top, NULL);
        fVisible = Polyline(hdc, aptStar, 6);

        return 0L;

    case WM_ERASEBKGND:

        // Erase the star.  

        fVisible = FALSE;
        return DefWindowProc(hwnd, message, wParam, lParam);

    case WM_DESTROY:
        KillTimer(hwnd, 1);
        PostQuitMessage(0);
        return 0L;
    }

    return DefWindowProc(hwnd, message, wParam, lParam);
}