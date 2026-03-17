from PyQt6.QtCore import Qt, pyqtSignal
from PyQt6.QtWidgets import QLabel

class ClickableLabel(QLabel):
    clicked = pyqtSignal()

    def mousePressEvent(self, event):
        if event.button() == Qt.MouseButton.LeftButton:
            self.clicked.emit()