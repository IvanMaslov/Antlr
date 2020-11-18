c = 1
def a(x):
    r = x + 1
    return r
d = a(c)
def b(y):
    c = y + 2
    return c
e = b(c)
print(d)
print(e)
