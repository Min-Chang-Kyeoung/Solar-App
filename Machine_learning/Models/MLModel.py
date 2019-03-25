import mongoengine

class RnnValue(mongoengine.Document):
    value = mongoengine.IntField()
