__author__ = 'achoudhary'

import operator

input = "abhaihshek"
list = list(input)

map = dict()
for key in list:
    if key in map:
        map[key] += 1
    else:
        map[key] = 1

map = sorted(map.items(), key=operator.itemgetter(1))
print(map)