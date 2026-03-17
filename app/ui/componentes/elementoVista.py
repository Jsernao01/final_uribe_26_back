from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QLabel, QPushButton,
    QVBoxLayout, QHBoxLayout, QScrollArea, QSizePolicy
)
from PyQt6.QtGui import QPixmap
from PyQt6.QtCore import Qt
import os
from datetime import datetime, timedelta
from ...logica.campos_editables import CampoEditable
from ...logica.label_clicleable import ClickableLabel
from ...logica.agregar_editables import editableAgregado
from .tablas_simples import tabla_simple

class DetalleElemento(QMainWindow):
  def __init__(self, controller,tipo, id, editable):
    super().__init__()
    self.controller = controller
    self.setWindowTitle("Detalles "+tipo)
    self.modoEdicion = False
    self.edicion_data = {"contenido":[], "widgets":[]}
    self.edicion_tablas = []
    self.recursos_agregables = []
    self.datos = None
    self.datos_dependiente = None
    self._cargar_datos(tipo, id)
    if tipo != "residencias":
      self.dependientes_info = {
        "nombre":"dependientes",
        "datos":{
          "key":["nombres","apellidos","tipoDocumento","documento","parentezco","ingresado"],
          "nombre":["Nombres","Apellidos","Tipo de Documento","Documento","Parentezco","Ingresado"],
          "tipo":["string","string","string","string","string","string","bool"],
          "editable":[True,True,True,True,True,True,True]
        },
        "valores":self.controller.consultarDependientesPorId(id),
        "idProfesional":"33333333-3333-3333-3333-333333333333"
      }

      self.medicamentos_info = {
        "nombre":"",
        "datos":{
          "key":["medicamentos"],
          "nombre":["Medicamentos"],
          "tipo":["string"],
          "editable":[True]
        },
        "valores":self.datos["condicionesMedicas"]["medicamentos"],
        "idProfesional":"33333333-3333-3333-3333-333333333333"
      }

      self.enfermedades_info = {
        "nombre":"",
        "datos":{
          "key":["enfermedades"],
          "nombre":["Enfermedades"],
          "tipo":["string"],
          "editable":[True]
        },
        "valores":self.datos["condicionesMedicas"]["enfermedades"],
        "idProfesional":"33333333-3333-3333-3333-333333333333"
      }

      self.condiciones_info = {
        "nombre":"",
        "datos":{
          "key":["condiciones"],
          "nombre":["Condiciones Medicas"],
          "tipo":["string"],
          "editable":[True]
        },
        "valores":self.datos["condicionesMedicas"]["condiciones"],
        "idProfesional":"33333333-3333-3333-3333-333333333333"
      }

    self.metodos_contacto_info = {
      "nombre":"Metodos de Contacto",
      "datos":{
        "key":["tipoMetodo", "contacto", "descripcion"],
        "nombre":["Tipo de Contacto", "Contacto", "descripción o Comentario"],
        "tipo":["string","string","string"],
        "editable":[True, True, True]
      },
      "valores":self.datos["metodosContacto"],
      "idProfesional":"33333333-3333-3333-3333-333333333333"
    }

    if tipo != "profesionales":
      
      data_comentarios = self.controller.consultarComentarios({
        "tipoRelacion":"USUARIAS",
        "idRelacion":id
      })

      self.comentarios_info = {
        "nombre":"Comentarios",
        "datos":{
          "key":["nombre", "profesion", "comentario", "fechaCreacion"],
          "nombre":["Comentadora", "Profesion", "Comentario", "Fecha"],
          "tipo":["string","string","string", "string"],
          "editable":[True, True, True, False]
        },
        "valores":data_comentarios,
        "idProfesional":"33333333-3333-3333-3333-333333333333"
      }



    self.setMinimumSize(800, 600)
    self._build_ui(tipo, editable, id)

  def _cargar_datos(self, tipo, id):
    if tipo != "residencias":
      self.datos = self.controller.consultarUsuaria(id)
    else:
      self.datos = self.controller.consultarResidencia(id)
    
    if tipo == "dependientes":
      self.datos_dependiente = self.controller.consultarDependientePorId(id)



  def crear_tarjeta_asignacion(self, titulo, residencia, fecha):
    tarjeta = QWidget()
    tarjeta.setStyleSheet("""
        background-color: transparent;
    """)
    tarjeta_l = QVBoxLayout(tarjeta)
    tarjeta_l.setSpacing(0)
    tarjeta_l.setContentsMargins(0, 0, 0, 0)

    header = QLabel(titulo)
    header.setStyleSheet("""
        background-color: #786C8C;
        font-weight: 600;
        color:white;
        padding: 8px;
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
    """)

    cuerpo = QWidget()
    cuerpo.setStyleSheet("""
        background-color: white;
        border: 1px solid #ddd;
        border-top: none;
        padding:4px;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
    """)
    cuerpo_l = QVBoxLayout(cuerpo)
    cuerpo_l.setContentsMargins(5, 4, 5, 4)

    cuerpo_l.addWidget(QLabel(f"<b>Residencia:</b> {residencia}"))
    cuerpo_l.addWidget(QLabel(f"<b>Fecha:</b> {fecha}"))

    tarjeta_l.addWidget(header, 2)
    tarjeta_l.addWidget(cuerpo, 3)

    return tarjeta

  def crear_tarjeta_medica(self, titulo, lista_items, texto_vacio):

    tarjeta = QWidget()
    tarjeta_l = QVBoxLayout(tarjeta)
    tarjeta_l.setSpacing(0)
    tarjeta_l.setContentsMargins(0, 0, 0, 0)

    header = QLabel(titulo)
    header.setAlignment(Qt.AlignmentFlag.AlignCenter)
    header.setStyleSheet("""
        background-color: #786C8C;
        font-weight: 600;
        color:white;
        padding: 8px;
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
    """)

    cuerpo = QWidget()
    cuerpo.setStyleSheet("""
        background-color: white;
        border: 1px solid #ddd;
        border-top: none;
        padding:4px;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
    """)
    cuerpo_l = QVBoxLayout(cuerpo)
    cuerpo_l.setContentsMargins(10, 8, 10, 8)
    cuerpo_l.setSpacing(6)

    if lista_items and len(lista_items) > 0:
      for item in lista_items:
        lbl = QLabel("• " + item)
        lbl.setAlignment(Qt.AlignmentFlag.AlignLeft)
        lbl.setWordWrap(True)
        cuerpo_l.addWidget(lbl)
    else:
      lbl = QLabel(texto_vacio)
      lbl.setAlignment(Qt.AlignmentFlag.AlignCenter)
      lbl.setStyleSheet("color:#7f8c8d; font-style: italic;")
      cuerpo_l.addWidget(lbl)

    tarjeta_l.addWidget(header)
    tarjeta_l.addWidget(cuerpo)

    return tarjeta

  def _build_ui(self, tipo, editable, id):

    BASE_DIR = os.path.dirname(os.path.abspath(__file__))

    direccion_agregar = os.path.join(BASE_DIR, "..", "..", "recursos", "iconos", "agregar.png")

    # ================== ESTILO GLOBAL ==================
    self.setStyleSheet("""
    QMainWindow {
        background-color: #f0f2f5;
        font-family: Segoe UI;
    }
    QLabel {
        color: #2c3e50;
        font-size: 13px;
    }
    QScrollArea {
        border: none;
    }
    """)

    central = QWidget()
    self.setCentralWidget(central)

    main_layout = QVBoxLayout(central)
    main_layout.setSpacing(0)
    main_layout.setContentsMargins(0, 0, 0, 0)

    # ---------------- Header ----------------
    header = QWidget()
    header.setFixedHeight(60)
    header.setStyleSheet("""
        background-color: white;
        border-bottom: 1px solid #ddd;
    """)

    header_layout = QHBoxLayout(header)
    header_layout.setContentsMargins(16, 0, 16, 0)

    titulo = (
        "la usuaria" if tipo == "usuarias"
        else "el dependiente" if tipo == "dependientes"
        else "la profesional" if tipo == "profesionales"
        else "la residencia"
    )

    lbl_title = QLabel("Detalles de " + titulo)
    lbl_title.setFixedWidth(300)
    lbl_title.setStyleSheet("font-size: 20px; font-weight: bold;")

    self.btn_editar = QPushButton("Editar")
    self.btn_editar.setCursor(Qt.CursorShape.PointingHandCursor)
    self.btn_editar.setStyleSheet("""
    background-color:#1976d2;
    color:white;
    border:none;
    padding:6px 16px;
    border-radius:6px;
    font-weight:600;
    """)
    self.btn_editar.clicked.connect(self.modo_edicion)

    header_layout.addWidget(lbl_title)
    header_layout.addStretch()
    if editable:
      header_layout.addWidget(self.btn_editar)

    main_layout.addWidget(header)

    # ---------------- Scroll ----------------
    scroll = QScrollArea()
    scroll.setWidgetResizable(True)

    contenido_scrolleable = QWidget()
    contenido_l = QVBoxLayout(contenido_scrolleable)
    contenido_l.setSpacing(10)
    contenido_l.setContentsMargins(10, 10, 10, 0)

    scroll.setWidget(contenido_scrolleable)
    main_layout.addWidget(scroll, 1)

    # ---------- Foto e información ----------
    info = QWidget()
    info.setFixedHeight(200)
    info.setStyleSheet("""
        background-color: white;
        border-radius: 8px;
    """)
    info_layout = QHBoxLayout(info)
    info_layout.setSpacing(20)

    
    direccion = None
    if tipo == "usuarias" or tipo == "dependientes":
      direccion = os.path.join(BASE_DIR, "..", "..", "recursos", "iconos", "icono_predeterminado_usuaria.png")
    if tipo == "profesionales":
      direccion = os.path.join(BASE_DIR, "..", "..", "recursos", "iconos", "icono_predeterminado_profesional.png")
    if tipo == "residencias":
      direccion = os.path.join(BASE_DIR, "..", "..", "recursos", "iconos", "icono_predeterminado_residencia.png")

    foto_seccion = QLabel()
    foto_seccion.setFixedHeight(200)
    foto_seccion.setAlignment(Qt.AlignmentFlag.AlignCenter)
    foto_seccion.setStyleSheet("border-radius: 100px;")

    pixmap = QPixmap(direccion).scaled(
        200, 200,
        Qt.AspectRatioMode.KeepAspectRatioByExpanding,
        Qt.TransformationMode.SmoothTransformation
    )
    foto_seccion.setPixmap(pixmap)

    info_seccion = QWidget()
    info_seccion_layout = QVBoxLayout(info_seccion)

    tipos_info = [
      {
        "tipo":"usuarias",
        "contenido":[
          {"titulo":"Tipo de Documento:", "valor":self.datos.get("tipoDocumento"), "editable":True, "key":"tipoDocumento", "tipo":"documentos"},
          {"titulo":"Documento:", "valor":self.datos.get("documento"), "editable":True, "key":"documento", "tipo":"string"},
          {"titulo":"Estado:", "valor":self.datos.get("estado"), "editable":False, "key":"", "tipo":""}
        ],
        "botones":["Procesos","Registros","Asignaciones","Documentos"]
      },
      {
        "tipo":"profesionales",
        "contenido":[
          {"titulo":"Tipo de Documento:", "valor":self.datos.get("tipoDocumento"), "editable":True, "key":"", "tipo":"documentos"},
          {"titulo":"Documento:", "valor":self.datos.get("documento"), "editable":True, "key":"", "tipo":"string"},
          {"titulo":"Estado:", "valor":self.datos.get("estado"), "editable":False, "key":"", "tipo":""},
          {"titulo":"Profesión:", "valor":self.datos.get("profesion"), "editable":True, "key":"", "tipo":"profesiones"}
        ],
        "botones":["Procesos","Horarios","Notificaciones","Documentos"]
      },
      {
        "tipo":"dependientes",
        "contenido":[
          {"titulo":"Tipo de Documento:", "valor":self.datos.get("tipoDocumento"), "editable":True, "key":"tipoDocumento", "tipo":"documentos"},
          {"titulo":"Documento:", "valor":self.datos.get("documento"), "editable":True, "key":"documento", "tipo":"string"},
          {"titulo":"Estado:", "valor":self.datos.get("estado"), "editable":False, "key":"", "tipo":""}
        ],
        "botones":[]
      },
      {
        "tipo":"residencias",
        "contenido":[
          {"titulo":"Nombre", "valor":self.datos.get("nombre"), "editable":True, "key":"", "tipo":"string"},
          {"titulo":"Direccion:", "valor":self.datos.get("direccion"), "editable":True, "key":"", "tipo":"string"},
          {"titulo":"Tipo de Residencia:", "valor":self.datos.get("tipoResidencia"), "editable":True, "key":"", "tipo":"tipoResidencia"}
        ],
        "botones":[]
      }
    ]

    for item in tipos_info:
      if item["tipo"] == tipo:
        for contenido in item["contenido"]:
          fila = QWidget()
          fila_l = QHBoxLayout(fila)
          titulo_lbl = QLabel(contenido["titulo"])
          titulo_lbl.setStyleSheet("color:#7f8c8d;")
          valor_lbl = None
          if contenido["editable"]:
            valor_lbl = CampoEditable(contenido["key"], contenido["tipo"], contenido["valor"])
            self.edicion_data["contenido"].append(valor_lbl.get_data())
            self.edicion_data["widgets"].append(valor_lbl)
          else:
            valor_lbl = QLabel(contenido["valor"]) 
          
          valor_lbl.setStyleSheet("font-weight:600;")
          fila_l.addWidget(titulo_lbl, 2)
          fila_l.addWidget(valor_lbl,3)
          info_seccion_layout.addWidget(fila)
    if tipo == "dependientes":
      fila_dep = QWidget()
      fila_dep_l = QHBoxLayout(fila_dep)

      titulo_lbl_dep = QLabel("¿Esta Ingresado?")
      titulo_lbl_dep.setStyleSheet("color:#7f8c8d;")

      fila_dep_l.addWidget(titulo_lbl_dep,2)

      contenido_dep = CampoEditable("ingresado", "bool", str(self.datos_dependiente["ingresado"]))
      self.edicion_data["contenido"].append(contenido_dep.get_data())
      self.edicion_data["widgets"].append(contenido_dep)
      contenido_dep.setStyleSheet("font-weight:600;")

      fila_dep_l.addWidget(contenido_dep,3)

      info_seccion_layout.addWidget(fila_dep)

    info_layout.addWidget(foto_seccion,2)
    info_layout.addWidget(info_seccion,6)
    contenido_l.addWidget(info)

    # ------ Nombre, edad y asignaciones (SIN FONDO) ------
    if(tipo != "residencias"):
      datos_asignaciones = QWidget()
      datos_asignaciones_l = QHBoxLayout(datos_asignaciones)

      # ===== Nombre y edad =====
      nombre_edad_w = QWidget()
      nombre_edad_l = QVBoxLayout(nombre_edad_w)

      nombres = None
      if editable:
        nombres = CampoEditable("nombres","string",self.datos["nombres"])
        self.edicion_data["contenido"].append(nombres.get_data())
        self.edicion_data["widgets"].append(nombres)
      else:
        nombres = QLabel(self.datos["nombres"]) 
      nombres.setStyleSheet("font-size: 28px; font-weight: bold;")

      apellidos = None
      if editable:
        apellidos = CampoEditable("apellidos", "string",self.datos["apellidos"])
        self.edicion_data["contenido"].append(apellidos.get_data())
        self.edicion_data["widgets"].append(apellidos)
      else:
        apellidos = QLabel(self.datos["apellidos"]) 
      apellidos.setStyleSheet("font-size: 22px;")

      edad = QWidget()
      edad_l = QHBoxLayout(edad)

      num_edad = None
      if editable:
        num_edad = CampoEditable("edad", "numerico", self.datos["edad"])
        self.edicion_data["contenido"].append(num_edad.get_data())
        self.edicion_data["widgets"].append(num_edad)
      else:
        num_edad = QLabel(self.datos["edad"])
      
      edad_text = QLabel(' años' if self.datos["edad"] else "Edad sin asignar")
      edad_l.addStretch()
      if self.datos["edad"]:
        edad_l.addWidget(num_edad)
      edad_l.addWidget(edad_text)
      edad_l.addStretch()

      edad.setStyleSheet("font-size: 18px; color:#555;")

      nombre_edad_l.addWidget(nombres)
      nombre_edad_l.addWidget(apellidos)
      nombre_edad_l.addWidget(edad)
      nombre_edad_l.addStretch()

      datos_asignaciones_l.addWidget(nombre_edad_w, 2)

      # ===== Asignación actual =====
      
      data_asignacion_actual= None
      if tipo == "profesionales":
        filtro_fecha_actual = {"dia": datetime.now().strftime("%Y-%m-%dT%H:%M"), "idProfesional":id}
        data_asignacion_actual = self.controller.consultarHorarios(filtro_fecha_actual)
      else:
        filtro_fecha_actual = {"fecha": datetime.now().strftime("%Y-%m-%dT%H:%M"), "idVictima":id}
        data_asignacion_actual = self.controller.consultarAsignaciones(filtro_fecha_actual)

      asignacion_actual = self.crear_tarjeta_asignacion(
          "Asignacion actual",
          data_asignacion_actual[0]["nombreResidencia"],
          datetime.fromisoformat(data_asignacion_actual[0]["fechaInicio"]).strftime("%d %b %Y · %H:%M")
      )if len(data_asignacion_actual) != 0 else QLabel("no hay asignaciones actuales registradas")

      # ===== Asignación próxima =====
      fecha_actual = datetime.fromisoformat(data_asignacion_actual[0]["fechaFinal"]) if len(data_asignacion_actual) != 0 else datetime.now() + timedelta(days=1)
      nuevos_filtros = None
      if tipo == "profesionales":
        nuevos_filtros = {
          "fecha": (datetime.now() + timedelta(days=1)).strftime("%Y-%m-%dT%H:%M"),
          "idVictima":id
        } 
      else:
        nuevos_filtros = {
          "fecha": (
              fecha_actual+ timedelta(days=1)
          ).strftime("%Y-%m-%dT%H:%M"),
          "idVictima":id
      }
      data_asignacion_proxima = None
      asignacion_proxima_valor = None

      if tipo == "profesionales":
        data_asignacion_proxima = self.controller.consultarHorarios(nuevos_filtros)
      else:
        data_asignacion_proxima = self.controller.consultarAsignaciones(nuevos_filtros)

      asignacion_proxima_valor = data_asignacion_proxima[0] if data_asignacion_proxima else None

      asignacion_proxima = self.crear_tarjeta_asignacion(
          "Asignación de mañana" if tipo == "profesionales" else "Asignacion Proxima",
          asignacion_proxima_valor["residencia"],
          datetime.fromisoformat(
              asignacion_proxima_valor["inicio"]
          ).strftime("%d %b %Y · %H:%M")
      ) if data_asignacion_proxima else QLabel("no hay asignaciones proximas registradas")

      datos_asignaciones_l.addWidget(asignacion_actual, 3)
      datos_asignaciones_l.addWidget(asignacion_proxima, 3)

      contenido_l.addWidget(datos_asignaciones)
      contenido_l.setStretchFactor(datos_asignaciones, 0)

    if tipo == "usuarias":
      # ------ dependientes ------ 

      tabla = tabla_simple(
        self.dependientes_info["nombre"],
        self.dependientes_info["datos"],
        self.dependientes_info["valores"],
        self.dependientes_info["idProfesional"],
      )

      self.edicion_tablas.append({
        "nombre":"dependientes",
        "contenido":tabla
      })
      contenido_l.addWidget(tabla)

    if tipo != "residencias":
      # ----- condiciones medicas --------

      condiciones_medicas_w = QWidget()
      condiciones_medicas_l = QHBoxLayout(condiciones_medicas_w)
      condiciones_medicas_l.setSpacing(20)

      enfermedades_tw = tabla_simple(
        "",
        self.enfermedades_info["datos"],
        self.enfermedades_info["valores"],
        self.enfermedades_info["idProfesional"]
      )

      self.edicion_tablas.append({
        "nombre":"enfermedades",
        "contenido":enfermedades_tw
      })

      condiciones_medicas_l.addWidget(enfermedades_tw)

      condiciones_medicas_tw = tabla_simple(
        "",
        self.condiciones_info["datos"],
        self.condiciones_info["valores"],
        self.condiciones_info["idProfesional"]
      )

      self.edicion_tablas.append({
        "nombre":"condiciones_medicas",
        "contenido":condiciones_medicas_tw
      })

      condiciones_medicas_l.addWidget(condiciones_medicas_tw)

      medicamentos_tw = tabla_simple(
        "",
        self.medicamentos_info["datos"],
        self.medicamentos_info["valores"],
        self.medicamentos_info["idProfesional"]
      )

      self.edicion_tablas.append({
        "nombre":"medicamentos",
        "contenido":medicamentos_tw
      })

      condiciones_medicas_l.addWidget(medicamentos_tw)

      contenido_l.addWidget(condiciones_medicas_w)

    # ------- metodos de contacto --------

    metodos_contacto_w = QWidget()
    metodos_contacto_l = QVBoxLayout(metodos_contacto_w)
    
    metodos_contacto_tw = tabla_simple(
        self.metodos_contacto_info["nombre"],
        self.metodos_contacto_info["datos"],
        self.metodos_contacto_info["valores"],
        self.metodos_contacto_info["idProfesional"]
      )

    self.edicion_tablas.append({
      "nombre":"metodos_contacto",
      "contenido":metodos_contacto_tw
    })

    metodos_contacto_l.addWidget(metodos_contacto_tw)
    
    contenido_l.addWidget(metodos_contacto_w)

    # ------ Comentarios ------
    if tipo != "profesionales":
      comentarios_w = QWidget()
      comentarios_l = QVBoxLayout(comentarios_w)

      comentario_tw = tabla_simple(
        self.comentarios_info["nombre"],
        self.comentarios_info["datos"],
        self.comentarios_info["valores"],
        self.comentarios_info["idProfesional"]
      )

      self.edicion_tablas.append({
        "nombre":"comentarios",
        "contenido":comentario_tw
      })

      comentarios_l.addWidget(comentario_tw)

      contenido_l.addWidget(comentarios_w)

    # ----- botones -----
    botones = QWidget()
    botones_l = QHBoxLayout(botones)
    botones_l.addStretch()

    for item in tipos_info:
      if item["tipo"] == tipo:
        for texto in item["botones"]:
          btn = QPushButton(texto)
          btn.setStyleSheet("""
            background-color:#e3f2fd;
            color:#1976d2;
            border:1px solid #1976d2;
            padding:8px;
            border-radius:10px;
          """)
          botones_l.addWidget(btn)
    
    contenido_l.addWidget(botones)
    self.activar_wordwrap_global()

  def activar_wordwrap_global(self):
    for label in self.findChildren(QLabel):
      label.setWordWrap(True)
      label.setAlignment(Qt.AlignmentFlag.AlignCenter)
      label.setSizePolicy(
        QSizePolicy.Policy.Expanding,
        QSizePolicy.Policy.Preferred
      )
    
  def modo_edicion(self):
    self.modoEdicion = not self.modoEdicion

    if self.modoEdicion:
      self.btn_editar.setText("Guardar")
      self.btn_editar.setStyleSheet("""
      background-color:#4EA357;
      color:white;
      border:none;
      padding:6px 16px;
      border-radius:6px;
      font-weight:600;
      """)
    else:
      self.btn_editar.setText("Editar")
      self.btn_editar.setStyleSheet("""
      background-color:#1976d2;
      color:white;
      border:none;
      padding:6px 16px;
      border-radius:6px;
      font-weight:600;
      """)

    for campo in self.edicion_data["widgets"]:
      campo.set_editable(self.modoEdicion)

    for campo in self.edicion_tablas:
      campo["contenido"].cambiar_estado(self.modoEdicion)  

    for recurso in self.recursos_agregables:
      recurso.setVisible(self.modoEdicion)

  def agregar_editables(self, campos, contenedor, oculto, mostrado):
    layout_editable = editableAgregado(campos)  
    contenedor_w = QWidget()
    contenedor_w.setLayout(layout_editable)
    contenedor.addWidget(contenedor_w)
    oculto.setVisible(False)
    mostrado.setVisible(True)



