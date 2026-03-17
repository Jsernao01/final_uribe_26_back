import sys
from PyQt6.QtWidgets import QApplication
from app.ui.main_window import MainWindow
from app.logica.controlador import Controller

def main():
    app = QApplication(sys.argv)
    app.setStyle("Fusion")
    controller = Controller()
    window = MainWindow(controller)
    window.show()
    sys.exit(app.exec())

if __name__ == "__main__":
    main()