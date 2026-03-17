from PyQt6.QtWidgets import (
    QWidget, QVBoxLayout, QFrame, QLabel,
    QLineEdit, QPushButton
)
from PyQt6.QtCore import Qt, pyqtSignal
from PyQt6.QtGui import QFont, QPixmap

from app.styles.colores import (
    PRIMARY, PRIMARY_DARK, TEXT_PRIMARY,
    CARD, INPUT_BORDER
)
import os

class LoginWidget(QWidget):
    login_success = pyqtSignal()

    def __init__(self, controller, parent=None):
        super().__init__(parent)

        self.controller = controller

        self.setObjectName("loginBackground")
        self.setAttribute(Qt.WidgetAttribute.WA_StyledBackground, True)

        self.setup_ui()
        self.setup_connections()
        self.apply_styles()

    # -------------------------------------------------
    # UI
    # -------------------------------------------------
    def setup_ui(self):
        main_layout = QVBoxLayout(self)
        main_layout.setAlignment(Qt.AlignmentFlag.AlignCenter)

        self.card = QFrame()
        self.card.setFixedSize(360, 420)
        self.card.setObjectName("loginCard")

        card_layout = QVBoxLayout(self.card)
        card_layout.setSpacing(15)
        card_layout.setContentsMargins(30, 30, 30, 30)
        card_layout.setAlignment(Qt.AlignmentFlag.AlignTop)

        BASE_DIR = os.path.dirname(os.path.abspath(__file__))
        logo_path = os.path.join(BASE_DIR, "..", ".." ,"recursos", "imagenes", "logo_grande.png")

        pixmap = QPixmap(logo_path).scaled(
        150, 150,
        Qt.AspectRatioMode.KeepAspectRatioByExpanding,
        Qt.TransformationMode.SmoothTransformation
        )
        self.logo = QLabel()
        self.logo.setPixmap(pixmap)
        self.logo.setAlignment(Qt.AlignmentFlag.AlignCenter)
        self.logo.setFont(QFont("Arial", 32))

        self.title = QLabel("Bienvenida a Métodos de Protección")
        self.title.setAlignment(Qt.AlignmentFlag.AlignCenter)
        self.title.setFont(QFont("Arial", 12, QFont.Weight.Bold))

        self.user_input = QLineEdit()
        self.user_input.setPlaceholderText("Documento")

        self.pass_input = QLineEdit()
        self.pass_input.setPlaceholderText("Contraseña")
        self.pass_input.setEchoMode(QLineEdit.EchoMode.Password)

        self.error_label = QLabel("")
        self.error_label.setObjectName("errorLabel")
        self.error_label.setAlignment(Qt.AlignmentFlag.AlignCenter)
        self.error_label.setVisible(False)

        self.login_button = QPushButton("Acceder")
        self.login_button.setCursor(Qt.CursorShape.PointingHandCursor)

        card_layout.addWidget(self.logo)
        card_layout.addWidget(self.title)
        card_layout.addSpacing(10)
        card_layout.addWidget(self.user_input)
        card_layout.addWidget(self.pass_input)
        card_layout.addWidget(self.error_label)
        card_layout.addSpacing(10)
        card_layout.addWidget(self.login_button)

        main_layout.addWidget(self.card)

    def setup_connections(self):
        self.login_button.clicked.connect(self.on_login)
        self.pass_input.returnPressed.connect(self.on_login)

    def on_login(self):
        usuario = self.user_input.text().strip()
        password = self.pass_input.text().strip()

        if not usuario or not password:
            self.show_error("Completa todos los campos")
            return

        response = self.controller.validar_login(usuario, password)
        if response["pasa"]:

            self.login_success.emit()
        else:
            self.show_error(response["mensaje"])
            

    def show_error(self, message):
        if message:
            self.error_label.setText(message)
            self.error_label.setVisible(True)
        else:
            self.error_label.setVisible(False)

    def apply_styles(self):
        self.setStyleSheet(f"""
        #loginBackground {{
            background-color: {PRIMARY};
        }}

        #loginCard {{
            background-color: {CARD};
            border-radius: 12px;
        }}

        QLabel {{
            color: {TEXT_PRIMARY};
        }}

        QLabel#errorLabel {{
            color: #d32f2f;
            font-size: 12px;
        }}

        QLineEdit {{
            background-color: transparent;
            border: none;
            border-bottom: 2px solid {INPUT_BORDER};
            padding: 8px;
            font-size: 14px;
            color: {TEXT_PRIMARY};
        }}

        QLineEdit:focus {{
            border-bottom: 2px solid {PRIMARY};
        }}

        QPushButton {{
            background-color: {PRIMARY};
            color: white;
            border-radius: 20px;
            padding: 10px;
            font-size: 14px;
        }}

        QPushButton:hover {{
            background-color: {PRIMARY_DARK};
        }}
        """)
