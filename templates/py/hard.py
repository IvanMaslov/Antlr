c = 1
def a(x):
    x = x * 2
    r = x + 1
    return r
def b(y):
    y = y * 2
    c = y + 2
    return c
if True or False:
    e = b(c) * a(4)
else:
    a(1)
print(e)
