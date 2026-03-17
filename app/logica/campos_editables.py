from PyQt6.QtWidgets import QWidget, QLabel, QLineEdit, QHBoxLayout, QComboBox
from .controlador import Controller
from PyQt6.QtGui import QIntValidator
class CampoEditable(QWidget):
    def __init__(self, key, tipo, valor):
        super().__init__()

        self.key = key  
        layout = QHBoxLayout(self)
        layout.setContentsMargins(0, 0, 0, 0)

        if tipo != "bool":
            self.lbl_valor = QLabel(str(valor).title() if valor else "")

            self.input = QLineEdit(str(valor).title() if valor else "")
            self.input.hide()
        else:
            self.lbl_valor = QLabel(("Si" if str(valor).title() == "True" else "No") if valor else "No")

            self.input = QLineEdit(str(valor).title() if valor else "")
            self.input.hide()

        self.combo = QComboBox()
        self.combo.hide()

        self.combo.setStyleSheet("""
            QComboBox {
                background-color: #f2f4f8;
                border: 1px solid #c5c9d3;
                border-radius: 6px;
                padding: 3px 3px;
                color: #2c2c2c;
            }

            QComboBox QAbstractItemView {
                background-color: #f2f4f8;
                border: 1px solid #c5c9d3;
                selection-background-color: #6aa9ff;
                selection-color: #ffffff;
                padding: 4px;
                outline: 0;
            }
            """)
        self.input.setStyleSheet("""
            QLineEdit {
                background-color: #f2f4f8;
                border: 1px solid #c5c9d3;
                border-radius: 6px;
                padding: 3px 3px;
                color: #2c2c2c;
            }
            QLineEdit:focus {
                border: 1px solid #6aa9ff;
                background-color: #ffffff;
            }
        """)
        self.visible = self.combo
        layout.addWidget(self.lbl_valor)
        if tipo == "string":
            layout.addWidget(self.input)
            self.visible = self.input

        if tipo == "numerico":
            self.input.setValidator(QIntValidator())
            layout.addWidget(self.input)
            self.visible = self.input
        
        if tipo == "bool":
            self.combo.addItem("Si", True)
            self.combo.addItem("No", False)
            self.combo.setCurrentText("Si" if str(valor).title() == "True" else "No")
            layout.addWidget(self.combo)
        
        if tipo == "documentos":
            for documento in Controller.consultarEnums("tipoDocumento"):
                self.combo.addItem(str(documento.replace("_", " ")).title(), documento)
            self.combo.setCurrentText(valor)
            layout.addWidget(self.combo)
        
        if tipo == "profesiones":
            for profesion in Controller.consultarProfesiones():
                self.combo.addItem(str(profesion["profesion"].replace("_"," ")).title(), profesion["idProfesion"])
            self.combo.setCurrentText(valor)
            layout.addWidget(self.combo)
        
        if tipo == "tipoResidencia":
            for residencia in Controller.consultarEnums("tipoResidencia"):
                self.combo.addItem(str(residencia.replace("_"," ")).title(), residencia) if str(residencia).title() != "Libre" else None
            self.combo.setCurrentText(valor)
            layout.addWidget(self.combo)

    def set_editable(self, editable: bool):
        self.lbl_valor.setVisible(not editable)
        self.visible.setVisible(editable)

    def get_data(self):
        return {self.key: self.input.text()}