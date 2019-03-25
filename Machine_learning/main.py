import Rnn_Elec
import numpy as np
from Models import MLModel
from pymongo import MongoClient

conn = MongoClient('127.0.0.1:27017')
mlValue = Rnn_Elec.execRnn()
str(mlValue)
with conn:
    db = conn.mongo
    rnnValue = db.rnnvalues
    db.rnnvalues.insert({"value":mlValue})


