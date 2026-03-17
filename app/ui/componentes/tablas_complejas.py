from PyQt6.QtWidgets import (
    QWidget, QVBoxLayout, QHBoxLayout,
    QTableWidget, QPushButton, QLabel, QFrame
)
from PyQt6.QtCore import Qt, QSize
from PyQt6.QtGui import QIcon
from ...logica.campos_editables import CampoEditable
from ..componentes.elementoVista import DetalleElemento
from ...logica.controlador import Controller
import os


class tabla_compleja(QWidget):
    def __init__(self, campos, data, page_size):
        super().__init__()

        self.controller = Controller()
        self.campos = campos
        self.data = data
        self.page_size = page_size
        self.current_page = 0

        # ───────── Layout raíz ─────────
        root = QVBoxLayout(self)
        root.setContentsMargins(0, 0, 0, 0)

        # ───────── Card ─────────
        card = QFrame()
        card.setObjectName("tableCard")
        card_layout = QVBoxLayout(card)
        card_layout.setContentsMargins(14, 14, 14, 14)
        card_layout.setSpacing(10)
        root.addWidget(card)

        # ───────── Tabla ─────────
        self.table = QTableWidget()
        self.table.setColumnCount(len(campos) + 1)
        self.table.setHorizontalHeaderLabels(
            [c["nombre"] for c in campos] + ["Acción"]
        )

        self.table.verticalHeader().setVisible(False)
        self.table.setEditTriggers(QTableWidget.EditTrigger.NoEditTriggers)
        self.table.setSelectionMode(QTableWidget.SelectionMode.NoSelection)
        self.table.setFocusPolicy(Qt.FocusPolicy.NoFocus)
        self.table.setShowGrid(False)

        header = self.table.horizontalHeader()
        header.setStretchLastSection(True)
        header.setSectionResizeMode(header.ResizeMode.Stretch)
        header.setDefaultAlignment(Qt.AlignmentFlag.AlignCenter)

        card_layout.addWidget(self.table)

        # ───────── Paginación ─────────
        pager = QHBoxLayout()

        self.btn_prev = QPushButton("◀")
        self.btn_next = QPushButton("▶")
        self.lbl_page = QLabel()
        self.lbl_page.setAlignment(Qt.AlignmentFlag.AlignCenter)

        for btn in (self.btn_prev, self.btn_next):
            btn.setFixedSize(44, 44)
            btn.setStyleSheet("font-size:20px;")
            btn.setCursor(Qt.CursorShape.PointingHandCursor)

        self.btn_prev.clicked.connect(self.prev_page)
        self.btn_next.clicked.connect(self.next_page)

        pager.addStretch()
        pager.addWidget(self.btn_prev)
        pager.addWidget(self.lbl_page)
        pager.addWidget(self.btn_next)
        pager.addStretch()

        card_layout.addLayout(pager)

        self.apply_styles()
        self.render()

    # ──────────────────────────────
    # Estilos
    # ──────────────────────────────
    def apply_styles(self):
        self.setStyleSheet("""
        QFrame#tableCard {
            background-color: #F7F6FA;
            border-radius: 14px;
            border: 1px solid #E0DCE8;
        }

        QTableWidget {
            background-color: transparent;
            border: none;
            font-size: 13px;
        }

        QHeaderView::section {
            background-color: #786C8C;
            color: white;
            font-weight: 600;
            padding: 10px;
        }

        QPushButton {
            border: none;
            background-color: #EDEAF3;
            border-radius: 22px;
            font-size: 16px;
            font-weight: bold;
        }

        QPushButton:hover {
            background-color: #DAD5E4;
        }
        """)

    # ──────────────────────────────
    # Render
    # ──────────────────────────────
    def render(self):
        BASE_DIR = os.path.dirname(os.path.abspath(__file__))
        edit_path = os.path.join(BASE_DIR, "..", "..", "recursos", "iconos", "editar.png")
        detail_path = os.path.join(BASE_DIR, "..", "..", "recursos", "iconos", "archivo.png")

        start = self.current_page * self.page_size
        end = start + self.page_size
        page_data = self.data[start:end]

        self.table.setRowCount(len(page_data))

        for row, item in enumerate(page_data):
            # ── Columnas de datos
            for col, campo in enumerate(self.campos):
                campo_widget = CampoEditable(
                    key=campo["key"],
                    tipo=campo["tipo"],
                    valor=item.get(campo["key"])
                )
                campo_widget.set_editable(False)

                cell = QWidget()
                cell_l = QHBoxLayout(cell)
                cell_l.setContentsMargins(0, 0, 0, 0)
                cell_l.setAlignment(Qt.AlignmentFlag.AlignCenter)
                cell_l.addWidget(campo_widget)

                self.table.setCellWidget(row, col, cell)

            # ── Acciones
            op_w = QWidget()
            op_l = QHBoxLayout(op_w)
            op_l.setContentsMargins(4, 4, 4, 4)
            op_l.setSpacing(12)
            op_l.setAlignment(Qt.AlignmentFlag.AlignCenter)

            btn_edit = QPushButton()
            btn_edit.setIcon(QIcon(edit_path))
            btn_edit.setIconSize(QSize(24, 24))
            btn_edit.setFixedSize(36, 36)
            btn_edit.setProperty("row", row)
            btn_edit.setProperty("editing", False)
            btn_edit.clicked.connect(self.toggle_edit_row)

            btn_detail = QPushButton()
            btn_detail.setIcon(QIcon(detail_path))
            btn_detail.setIconSize(QSize(24, 24))
            btn_detail.setFixedSize(36, 36)
            btn_detail.clicked.connect(
                lambda _, id=item["id"]: self.abrir_detalle("usuarias", id)
            )

            op_l.addWidget(btn_edit)
            op_l.addWidget(btn_detail)

            self.table.setCellWidget(row, len(self.campos), op_w)
            self.table.setRowHeight(row, 70)

        self.table.setColumnWidth(len(self.campos), 110)
        self.lbl_page.setText(f"{self.current_page + 1} / {self.total_pages()}")

    # ──────────────────────────────
    # Acciones
    # ──────────────────────────────
    def abrir_detalle(self, tipo, id):
        self.detalle = DetalleElemento(self.controller, tipo, id, True)
        self.detalle.show()

    def toggle_edit_row(self):
        btn = self.sender()
        row = btn.property("row")
        editing = btn.property("editing")

        btn.setProperty("editing", not editing)

        for col, campo in enumerate(self.campos):
            if not campo.get("editable"):
                continue

            cell = self.table.cellWidget(row, col)
            widget = cell.layout().itemAt(0).widget()
            widget.set_editable(not editing)

    # ──────────────────────────────
    # Paginación
    # ──────────────────────────────
    def total_pages(self):
        return max(1, (len(self.data) + self.page_size - 1) // self.page_size)

    def next_page(self):
        if self.current_page < self.total_pages() - 1:
            self.current_page += 1
            self.render()

    def prev_page(self):
        if self.current_page > 0:
            self.current_page -= 1
            self.render()