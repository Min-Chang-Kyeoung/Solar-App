import tensorflow as tf
from openpyxl import load_workbook

elecEx = load_workbook(filename='./electric/elecdata.xlsx')

sheet1 = elecEx['Sheet1']


print(sheet1['A1'].value)

electData = []

for i in sheet1.rows:
    num = i[0].value
    usage = i[1].value
    cost = i[2].value
    datas = (num,usage,cost)
    electData.append(datas)

print(electData)