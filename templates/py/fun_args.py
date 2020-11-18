c = 1
def a(x, u):
    r = x + 1
    return r + u
d = a(c, 1)
def b(y, z, q):
    c = y + 2
    z = z + q
    e = z * 2
    return c + z * q - e
e = b(c, 4, c / 2)
print(d)
print(e)
