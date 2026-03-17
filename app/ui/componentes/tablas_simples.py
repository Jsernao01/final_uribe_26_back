from PyQt6.QtWidgets import QWidget, QHBoxLayout, QVBoxLayout, QLabel, QPushButton
from PyQt6.QtGui import QPixmap
from PyQt6.QtCore import Qt
import os
from ...logica.campos_editables import CampoEditable
from ...logica.label_clicleable import ClickableLabel
from ...logica.agregar_editables import editableAgregado

class tabla_simple(QWidget):
  def __init__(self, nombre, datos, valores, idProfesional ):
    super().__init__()

    #datos = key[], nombre[], tipo[], editable
    BASE_DIR = os.path.dirname(os.path.abspath(__file__))
    self.valores_editados = []
    self.visibles = []
    self.permitidos = []
    self.escondidos = []
    self.nombre = nombre
    direccion_agregar = os.path.join(BASE_DIR, "..", "..", "recursos", "iconos", "agregar.png")
    total_l = QVBoxLayout()

    principal_W = QWidget()
    principal_l = QVBoxLayout(principal_W)
    principal_l.setSpacing(0)
    principal_l.setContentsMargins(0, 0, 0, 0)

    contenido_w = QWidget()
    contenido_w.setStyleSheet("""
        background-color: white;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
        padding: 4px;
        """)
    contenido_l = QVBoxLayout(contenido_w)

    titulo = QWidget()
    titulo_l = QHBoxLayout(titulo)

    principal_titulo = QLabel(f'<b>{nombre.title()}</b>')
    principal_titulo.setFixedWidth(500)
    principal_titulo.setStyleSheet("font-size:20px;")

    boton_agregar = ClickableLabel()
    pixmap = QPixmap(direccion_agregar).scaled(
      20, 20,
      Qt.AspectRatioMode.KeepAspectRatioByExpanding,
      Qt.TransformationMode.SmoothTransformation
    )
    boton_agregar.setPixmap(pixmap)
    boton_agregar.hide()
    self.visibles.append(boton_agregar) 
    
    if nombre != "":
      titulo_l.addStretch()
      titulo_l.addWidget(principal_titulo)
      titulo_l.addWidget(boton_agregar)
      titulo_l.addStretch()

      total_l.addWidget(titulo) 

    principal_columnas_w = QWidget()
    principal_columnas_w.setFixedHeight(50)
    principal_columnas_l = QHBoxLayout(principal_columnas_w)

    principal_columnas_w = QWidget()
    self.permitidos.append(principal_columnas_w)
    principal_columnas_w.setStyleSheet("""
        background-color: #786C8C;
        font-weight: 600;
        color:white;
        padding: 4px;
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
    """)
    principal_columnas_l = QHBoxLayout(principal_columnas_w)
    boton_agregar.setCursor(Qt.CursorShape.PointingHandCursor)
    boton_agregar.clicked.connect(lambda: self.agregar_edicion(datos, contenido_l, nombre))
    
    for dato in datos["nombre"]:
      columna = QLabel(f'<b>{dato}</b>')
      principal_columnas_l.addWidget(columna, 3)
    operaciones_t= QLabel("operaciones")
    operaciones_t.setStyleSheet("background:#786C8C; color:white;  border-top-left-radius: 8px; border-top-right-radius: 8px;")
    operaciones_t.hide()
    if nombre != "":
      principal_columnas_l.addWidget(operaciones_t)
      self.visibles.append(operaciones_t)
    else:
      principal_columnas_l.addWidget(boton_agregar, 1)
    no_data = QLabel(f'No hay {nombre if nombre != "" else datos["nombre"][0]} relacionados para esta usuaria')
    self.escondidos.append(no_data)
    no_data.setStyleSheet("""
        color:#7f8c8d;
        background-color: white;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
        padding: 4px;
        """)
    
    if nombre == "":
      if len(valores) != 0 :
        no_data.hide()
    else:
      if len(valores) == 0 :
        principal_columnas_w.hide()
      else:
        no_data.hide()


    principal_l.addWidget(principal_columnas_w)
    principal_l.addWidget(no_data)
    # boton_agregar.clicked.connect( lambda: self.agregar_editables(["1","2","3","4","5","6"], principal_l, no_data, principal_columnas_w))#---------------------

    principal_l.addWidget(contenido_w)
    for registro in valores:
      registro_w = QWidget()
      registro_l = QHBoxLayout(registro_w)

      eliminar_btn = QPushButton("Eliminar")
      eliminar_btn.setCursor(Qt.CursorShape.PointingHandCursor)
      eliminar_btn.setStyleSheet("""
          QPushButton {
              background-color: #FDECEA; 
              color: #F44336;
              font-size: 14px;
              font-weight: bold;
              border: 2px solid #F44336;
              border-radius: 8px;
              padding: 2px;
          }
          QPushButton:hover {
              background-color: #F9BDBB;
          }
          QPushButton:pressed {
              background-color: #F69988;
          }
      """)

      self.visibles.append(eliminar_btn)

      operavilidad = True

      if nombre == "comentarios":
        if registro["idProfesional"] != idProfesional:
          operavilidad = False

      if nombre != "":
        for i, dato in enumerate(datos["key"]):
          lbl = None
          if operavilidad:
            lbl = CampoEditable(dato, datos["tipo"][i],registro[dato])
            self.valores_editados.append({"key":dato, "valor": lbl})
          else:
            if datos["tipo"][i] != "bool":
              lbl = QLabel(registro[dato])
            else:
              lbl = QLabel("Si" if registro[dato] == "True" else "No")
          lbl.setStyleSheet("font-weight:600;")
          registro_l.addWidget(lbl, 1)
      else:
        lbl = None
        if operavilidad:
          lbl = CampoEditable(datos["key"][0], datos["tipo"][0],registro)
          self.valores_editados.append({"key":datos["key"][0], "valor": lbl})
        else:
          lbl = QLabel(registro)
          lbl.setStyleSheet("font-weight:600;")
        registro_l.addWidget(lbl, 3)
    
      eliminar_btn.hide()

      if nombre != "":
        registro_l.addWidget(eliminar_btn, 1)
      else:
        registro_l.addWidget(eliminar_btn, 1)

      contenido_l.addWidget(registro_w)

      
    if nombre != "":
      total_l.addWidget(principal_W)
      self.setLayout(total_l)
    else:
      self.setLayout(principal_l)

  def cambiar_estado(self, estado:bool):
    for campo in self.valores_editados:
      campo["valor"].set_editable(estado)

    for widget in self.visibles:
      widget.setVisible(estado)

    if len(self.valores_editados) == 0:
      if self.nombre != "":
        for permitido in self.permitidos:
          permitido.setVisible(estado)
      for escondido in self.escondidos:
        escondido.setVisible(not estado)

  def agregar_edicion(self, datos, contenedor, nombre):

    nueva_fila_w = QWidget()
    nueva_fila_l = QHBoxLayout(nueva_fila_w)

    if nombre != "":
      for i, dato in enumerate(datos["key"]):
        lbl = CampoEditable(dato, datos["tipo"][i],"")
        self.valores_editados.append({"key":dato, "valor": lbl})
        lbl.set_editable(True)
        lbl.setStyleSheet("font-weight:600;")
        nueva_fila_l.addWidget(lbl, 1)
    else:
      lbl = CampoEditable(datos["key"][0], datos["tipo"][0],"")
      self.valores_editados.append({"key":datos["key"][0], "valor": lbl})
      lbl.set_editable(True)
      lbl.setStyleSheet("font-weight:600;")
      nueva_fila_l.addWidget(lbl, 3)
    eliminar_btn = QPushButton("Eliminar")
    eliminar_btn.setCursor(Qt.CursorShape.PointingHandCursor)
    eliminar_btn.setStyleSheet("""
        QPushButton {
            background-color: #FDECEA;   
            color: #F44336;
            font-size: 14px;
            font-weight: bold;
            border: 2px solid #F44336;
            border-radius: 8px;
            padding: 2px;
        }
        QPushButton:hover {
            background-color: #F9BDBB;
        }
        QPushButton:pressed {
            background-color: #F69988;
        }
    """)
    
    self.visibles.append(eliminar_btn)

    nueva_fila_l.addWidget(eliminar_btn, 1)
    contenedor.insertWidget(0, nueva_fila_w)