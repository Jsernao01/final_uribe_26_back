from PyQt6.QtWidgets import (
    QWidget, QVBoxLayout, QHBoxLayout,
    QLabel, QLineEdit, QPushButton,
    QCheckBox, QFrame
)
from PyQt6.QtGui import QIntValidator
from PyQt6.QtCore import Qt, pyqtSignal


class FormularioDinamico(QWidget):

    submitted = pyqtSignal(dict)  # 👈 señal al enviar

    def __init__(self, campos: list, parent=None):
        super().__init__(parent)

        self.campos = campos
        self.inputs = {}         # key -> widget
        self.obligatorios = set()

        self.main_layout = QVBoxLayout(self)
        self.main_layout.setSpacing(18)
        self.main_layout.setAlignment(Qt.AlignmentFlag.AlignTop)

        self._build_form()
        self._build_footer()

    # ──────────────────────────────
    # Construcción del formulario
    # ──────────────────────────────
    def _build_form(self):
        secciones = {}

        for campo in self.campos:
            secciones.setdefault(campo["seccion"], []).append(campo)

        for seccion, campos in secciones.items():
            self._build_section(seccion, campos)

    def _build_section(self, titulo, campos):
        card = QFrame()
        card.setStyleSheet("""
            QFrame {
                background-color: #F7F6FA;
                border: 1px solid #E0DCE8;
                border-radius: 12px;
            }
        """)

        layout = QVBoxLayout(card)
        layout.setSpacing(12)

        title = QLabel(titulo)
        title.setStyleSheet("""
            font-size: 15px;
            font-weight: 600;
            color: #5E546F;
        """)
        layout.addWidget(title)

        for campo in campos:
            layout.addLayout(self._build_field(campo))

        self.main_layout.addWidget(card)

    def _build_field(self, campo):
        row = QHBoxLayout()
        row.setSpacing(8)

        label = QLabel(campo["nombre"])
        label.setMinimumWidth(140)
        label.setStyleSheet("color: #3E3A47;")

        row.addWidget(label)

        if campo.get("obligatorio"):
            star = QLabel("*")
            star.setStyleSheet("color: red; font-weight: bold;")
            row.addWidget(star)
            self.obligatorios.add(campo["key"])
        else:
            row.addSpacing(10)

        input_widget = self._create_input(campo)
        row.addWidget(input_widget, 1)

        self.inputs[campo["key"]] = input_widget

        return row

    def _create_input(self, campo):
        tipo = campo["tipo"]

        if tipo == "string":
            inp = QLineEdit()
            inp.setPlaceholderText(campo["nombre"])

        elif tipo == "numerico":
            inp = QLineEdit()
            inp.setValidator(QIntValidator())
            inp.setPlaceholderText(campo["nombre"])

        elif tipo == "booleano":
            inp = QCheckBox("Sí")

        else:
            raise ValueError(f"Tipo no soportado: {tipo}")

        inp.setStyleSheet("""
            QLineEdit {
                padding: 6px;
                border-radius: 6px;
                border: 1px solid #C9C3D3;
            }
            QLineEdit:focus {
                border: 1px solid #786C8C;
            }
        """)

        return inp

    # ──────────────────────────────
    # Footer (error + botón)
    # ──────────────────────────────
    def _build_footer(self):
        self.error_label = QLabel("")
        self.error_label.setStyleSheet("color: red; font-size: 12px;")
        self.error_label.setAlignment(Qt.AlignmentFlag.AlignCenter)

        self.submit_btn = QPushButton("Guardar")
        self.submit_btn.setFixedHeight(36)
        self.submit_btn.setStyleSheet("""
            QPushButton {
                background-color: #786C8C;
                color: white;
                border-radius: 8px;
                font-weight: 600;
                padding:8px;
            }
            QPushButton:hover {
                background-color: #6A5F80;
            }
        """)

        self.submit_btn.clicked.connect(self._on_submit)

        self.main_layout.addWidget(self.error_label)
        self.main_layout.addWidget(self.submit_btn, alignment=Qt.AlignmentFlag.AlignCenter)

    # ──────────────────────────────
    # Lógica
    # ──────────────────────────────
    def _on_submit(self):
        data = self.get_data()
        if data is not None:
            self.submitted.emit(data)

    def get_data(self):
        """
        Retorna:
        - dict con datos válidos
        - None si faltan obligatorios
        """
        data = {}
        faltantes = []

        for key, widget in self.inputs.items():
            if isinstance(widget, QLineEdit):
                value = widget.text().strip()
            elif isinstance(widget, QCheckBox):
                value = widget.isChecked()
            else:
                continue

            if key in self.obligatorios and not value:
                faltantes.append(key)
                continue

            if value not in ("", False):
                data[key] = value

        if faltantes:
            self.error_label.setText(
                "⚠️ Faltan campos obligatorios por rellenar"
            )
            return None

        self.error_label.setText("")
        return data