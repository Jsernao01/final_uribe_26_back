from PyQt6.QtWidgets import QMainWindow
from PyQt6.QtGui import QIcon
from .componentes.login import LoginWidget
from .componentes.dashboard import Dashboard
from .componentes.elementoVista import DetalleElemento
from .componentes.tablas_complejas import tabla_compleja
import os

class MainWindow(QMainWindow):
    def __init__(self, controller, parent=None):
        super().__init__(parent)

        self.controller = controller  

        BASE_DIR = os.path.dirname(os.path.abspath(__file__))
        logo_path = os.path.join(BASE_DIR, ".." ,"recursos","imagenes", "logo.png")

        icon = QIcon(logo_path)
        self.setWindowIcon(icon)
        self.setWindowTitle("Login")
        self.resize(900, 600)
        self.show_login()
        
    def show_login(self):

        
        self.controller.logout()
        self.login = LoginWidget(self.controller)
        self.login.login_success.connect(self.show_dashboard)
        self.setCentralWidget(self.login)

    def show_dashboard(self):
        self.dashboard = Dashboard(self.controller)
        self.dashboard.logout_requested.connect(self.show_login)
        self.setCentralWidget(self.dashboard)

