from PyQt6.QtWidgets import (
    QWidget, QVBoxLayout, QHBoxLayout, QLabel,
    QPushButton, QFrame
)
from PyQt6.QtCore import Qt, pyqtSignal
from PyQt6.QtGui import QFont
from functools import partial

from app.styles.colores import (
    PRIMARY, PRIMARY_DARK, CARD, HOVER_BG
)

from ..componentes.tablas_complejas import tabla_compleja
from ..componentes.elementoVista import DetalleElemento
from ...logica.label_clicleable import ClickableLabel
from ..componentes.formularioDinamico import FormularioDinamico


MENUS = [
    {
        "title": "Procesos",
        "items": [
            {"label": "Registrar usuaria", "view": "registrarUsuaria", "estadisticas":{}},
            {"label": "Registrar caso", "view": "registrarCaso", "estadisticas":{}},
            {"label": "Asignar usuaria", "view": "asignarUsuaria", "estadisticas":{}},
            {"label": "Crear proceso", "view": "crearProceso", "estadisticas":{}},
        ]
    },
    {
        "title": "Biblioteca",
        "items": [
            {
                "label": "Usuarias", 
                "view": "usuarias",
                "estadisticas":{
                    "campos":[
                        {"nombre": "Nombres", "key": "nombres", "tipo": "string", "editable": True},
                        {"nombre": "Apellidos", "key": "apellidos", "tipo": "string", "editable": True},
                        {"nombre": "Tipo de Documento", "key": "tipoDocumento", "tipo": "documentos", "editable": True},
                        {"nombre": "documento", "key": "documento", "tipo": "numerico", "editable": True},
                        {"nombre": "Activo", "key": "activo", "tipo": "bool", "editable": True},
                    ],
                    "filtros":{"tipo":"victima"},

                }
             },
            {"label": "Profesionales", "view": "profesionales", "estadisticas":{}},
            {"label": "Residencias", "view": "residencias", "estadisticas":{}},
            {"label": "Procesos", "view": "procesos", "estadisticas":{}},
        ]
    }
]


class Dashboard(QWidget):
    logout_requested = pyqtSignal()

    def __init__(self, controller, parent=None):
        super().__init__(parent)

        self.controller = controller
        self.idProfesional = self.controller.usuario_actual["idUsuaria"]
        self.dropdowns = []
        self.active_dropdown = None

        self.setObjectName("dashboard")
        self.setAttribute(Qt.WidgetAttribute.WA_StyledBackground, True)

        self.setup_ui()
        self.apply_styles()
        self.load_user_info()
        self.show_dashboard()

        self.logout_btn.clicked.connect(self.on_logout)

    # ================= USER =================
    def load_user_info(self):
        user = self.controller.usuario_actual
        if user:
            self.user_label.setText(f"👤 {user['nombre']} ({user['area']})")
            self.user_label.setCursor(Qt.CursorShape.PointingHandCursor)
            self.user_label.setObjectName("menuTitle")
            self.user_label.clicked.connect(lambda: self.abrir_detalle("profesionales", "33333333-3333-3333-3333-333333333333"))

    # ================= UI =================
    def setup_ui(self):
        self.main_layout = QVBoxLayout(self)
        self.main_layout.setContentsMargins(0, 0, 0, 0)

        # ---------- HEADER ----------
        self.header = QFrame()
        self.header.setFixedHeight(60)
        self.header.setObjectName("header")

        header_layout = QHBoxLayout(self.header)
        header_layout.setContentsMargins(20, 0, 20, 0)
        header_layout.setSpacing(25)

        inicio = ClickableLabel("Inicio")
        inicio.setObjectName("menuTitle")
        inicio.setCursor(Qt.CursorShape.PointingHandCursor)
        inicio.clicked.connect(self.show_dashboard)
        header_layout.addWidget(inicio)

        for menu in MENUS:
            label = QLabel(menu["title"])
            label.setObjectName("menuTitle")
            label.setCursor(Qt.CursorShape.PointingHandCursor)
            self._create_dropdown(label, menu["items"], menu["title"])
            header_layout.addWidget(label)

        header_layout.addStretch()

        self.user_label = ClickableLabel("👤")
        self.user_label.setFont(QFont("Arial", 12))

        self.logout_btn = QPushButton("Cerrar sesión")
        self.logout_btn.setObjectName("logoutButton")

        header_layout.addWidget(self.user_label)
        header_layout.addWidget(self.logout_btn)

        self.main_layout.addWidget(self.header)

        # ---------- CONTENT ----------
        self.content = QFrame()
        self.content.setObjectName("contentArea")

        self.content_layout = QVBoxLayout(self.content)
        self.content_layout.setAlignment(Qt.AlignmentFlag.AlignTop)

        self.main_layout.addWidget(self.content)

    # ================= TITULO DE SECCIÓN =================
    def build_section_frame(self, title_text):
        frame = QFrame()
        frame.setObjectName("sectionFrame")

        layout = QVBoxLayout(frame)
        layout.setContentsMargins(0, 0, 0, 0)

        header = QFrame()
        header.setFixedHeight(45)

        h_layout = QHBoxLayout(header)
        h_layout.setContentsMargins(16, 0, 0, 0)

        label = QLabel(title_text)
        label.setStyleSheet(f"""
            color: {PRIMARY};
            padding: 8px 12px;
            font-size: 18px;
            font-weight: 700;
            letter-spacing: 0.5px;
        """)
        label.setAlignment(Qt.AlignmentFlag.AlignVCenter)

        h_layout.addWidget(label)
        h_layout.addStretch()

        layout.addWidget(header)

        return frame, layout

    # ================= DROPDOWNS =================
    def _create_dropdown(self, title_label, items, section):
        dropdown = QFrame(self)
        dropdown.setObjectName("dropdown")
        dropdown.setVisible(False)
        dropdown.setWindowFlags(Qt.WindowType.Popup)

        layout = QVBoxLayout(dropdown)
        layout.setContentsMargins(0, 5, 0, 5)

        for item in items:
            btn = QPushButton(item["label"])
            btn.setObjectName("dropdownItem")

            btn.clicked.connect(
                partial(self.load_view, section, item["label"], item["view"], item["estadisticas"])
            )

            layout.addWidget(btn)

        self.dropdowns.append(dropdown)
        title_label.mousePressEvent = lambda e, d=dropdown, t=title_label: self.toggle_dropdown(d, t)

    def toggle_dropdown(self, dropdown, title_label):
        for d in self.dropdowns:
            if d != dropdown:
                d.hide()

        if dropdown.isVisible():
            dropdown.hide()
        else:
            pos = title_label.mapToGlobal(title_label.rect().bottomLeft())
            dropdown.move(pos)
            dropdown.show()
            dropdown.raise_()

    # ================= CONTENT =================
    def clear_content(self):
        while self.content_layout.count():
            item = self.content_layout.takeAt(0)
            if item.widget():
                item.widget().deleteLater()

    def show_dashboard(self):
        self.clear_content()

        title = QLabel("👋 Bienvenido al sistema")
        title.setAlignment(Qt.AlignmentFlag.AlignCenter)
        title.setStyleSheet("font-size: 26px; font-weight: bold; margin-top: 40px;")

        subtitle = QLabel("Selecciona una opción del menú para comenzar")
        subtitle.setAlignment(Qt.AlignmentFlag.AlignCenter)
        subtitle.setStyleSheet("color: #666;")

        self.content_layout.addWidget(title)
        self.content_layout.addWidget(subtitle)

    def load_view(self, section, nombre, view_name, estadisticas):
        self.clear_content()
        print(nombre)
        section_frame, section_layout = self.build_section_frame(nombre)
        self.content_layout.addWidget(section_frame)

        # ---------- PROCESOS ----------
        if section == "Procesos":
            campos = [
                {"key": "nombre", "nombre": "Nombre del proceso", "tipo": "string", "obligatorio": True, "seccion": "Datos generales"},
                {"key": "descripcion", "nombre": "Descripción", "tipo": "string", "obligatorio": False, "seccion": "Datos generales"},
                {"key": "activo", "nombre": "Activo", "tipo": "booleano", "obligatorio": True, "seccion": "Configuración"},
            ]

            formulario = FormularioDinamico(campos)
            formulario.submitted.connect(lambda data: print("FORM DATA:", data))
            section_layout.addWidget(formulario)

        # ---------- BIBLIOTECA ----------
        else:
            data = None
            if view_name == "usuarias":
                data = self.controller.consultarUsuarias(estadisticas["filtros"])

            if view_name == "profesionales":
                return #aqui
            print(data)
            section_layout.addWidget(tabla_compleja(estadisticas["campos"], data, 5))

        for d in self.dropdowns:
            d.hide()

    # ================= LOGOUT =================
    def abrir_detalle(self, tipo, id):
        self.detalle = DetalleElemento(
            self.controller,
            tipo,
            id,
            True
        )
        self.detalle.show()

    def on_logout(self):
        self.logout_requested.emit()

    # ================= STYLES =================
    def apply_styles(self):
        self.setStyleSheet(f"""
        #dashboard {{
            background-color: {CARD};
        }}

        #header {{
            background-color: {PRIMARY};
        }}

        #sectionHeader {{
            background-color: {PRIMARY};
            border-radius: 8px;
            margin: 15px 20px 10px 20px;
        }}

        QLabel#menuTitle {{
            color: white;
            padding: 8px 12px;
            font-weight: bold;
        }}

        QLabel#menuTitle:hover {{
            background-color: {PRIMARY_DARK};
            border-radius: 6px;
        }}

        #dropdown {{
            background-color: white;
            border-radius: 6px;
            min-width: 180px;
        }}

        QPushButton#dropdownItem {{
            background-color: transparent;
            text-align: left;
            padding: 8px 15px;
            border: none;
        }}

        QPushButton#dropdownItem:hover {{
            background-color: {HOVER_BG};
        }}

        #logoutButton {{
            background-color: transparent;
            color: white;
            border: 1px solid white;
            padding: 6px 12px;
            border-radius: 12px;
        }}

        #logoutButton:hover {{
            background-color: white;
            color: {PRIMARY};
        }}
        """)