import requests;
from ..enviorement import ruta, asignacionesProfesionales, asignacionesVictimas,comentarios, notificaciones, procesos, registros, residencias, usuarias, verificaciones, enums

class Controller:
    def __init__(self):
        self.usuario_actual = None

    def validar_login(self, usuario, password):

        url = ruta+verificaciones+"/login"

        payload = {
            "documento":usuario,
            "contrasena":password
        }

        response = requests.post(url, json=payload)

        if response.json()["pasa"]:

            token = requests.get(ruta+verificaciones+"/claims/"+response.json()["mensaje"])
            data = token.json()
            self.usuario_actual = {
                "nombre":data["nombres"],
                "idUsuaria":data["idUsuaria"],
                "area":data["areaAsignada"],
                "expiracion":data["expiracion"]
            }

        return response.json()
        
    def logout(self):
        self.usuario_actual = None

    def consultarUsuaria(self, idUsuaria):
        url = ruta + usuarias + "/" + idUsuaria
        response = requests.get(url) 
        return response.json()
    
    def consultarUsuarias(self, filtros):
        url = ruta + usuarias + "/filtrado"
        response = requests.get(url, json=filtros)
        print(response.json())
        return response.json()

    def consultarResidencia(self, idResidencia):
        url = ruta + residencias +"/"+ idResidencia
        response = requests.get(url)
        return response.json()
    
    def consultarAsignaciones(self, filtros):
        url = ruta + asignacionesVictimas + "/filtrado"
        response = requests.get(url, json=filtros)
        return response.json()
    
    def consultarHorarios(self, filtros):
        url = ruta + asignacionesProfesionales + "/filtrado"
        response = requests.get(url, json=filtros)
        return response.json()
    
    def consultarComentarios(self, filtros):
        url = ruta + comentarios + "/filtrado"
        response = requests.get(url, json=filtros)
        return response.json()
    
    def consultarDependientesPorId(self, idUsuaria):
        url = ruta + usuarias + "/dependientes/" + idUsuaria
        response = requests.get(url)
        return response.json()
    
    def consultarDependientePorId(self, idDependiente):
        url = ruta + usuarias + "/dependiente/" + idDependiente
        response = requests.get(url)
        return response.json()
    
    def consultarEnums( enum):
        url = ruta + enums + enum
        response = requests.get(url)
        return response.json()    

    def consultarProfesiones():
        url = ruta + verificaciones + "/profesiones"  
        response = requests.get(url)
        return response.json()  