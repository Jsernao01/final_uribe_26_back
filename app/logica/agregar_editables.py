from PyQt6.QtWidgets import QHBoxLayout, QLineEdit

class editableAgregado(QHBoxLayout):
  def __init__(self, campos):
    super().__init__()
    self.campos = campos
    self.inputs = {}

    for campo in self.campos:
      input_ = QLineEdit()
      input_.setStyleSheet("""
          QLineEdit {
              background-color: #f2f4f8;
              border: 1px solid #c5c9d3;
              border-radius: 6px;
              padding: 6px 8px;
              color: #2c2c2c;
          }
          QLineEdit:focus {
              border: 1px solid #6aa9ff;
              background-color: #ffffff;
          }
      """)

      self.addWidget(input_)
      self.inputs[campo] = input_
  
  def sacarObjeto(self):
    return {
      campo: input_.text()
      for campo, input_ in self.inputs.items()
    }