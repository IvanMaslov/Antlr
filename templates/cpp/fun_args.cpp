#include<iostream>
int c;
int d;
int e;
int a(int x, int u){
	int r;
	r = x + 1;
	return r + u;
}
int b(int y, int z, int q){
	int c;
	int e;
	c = y + 2;
	z = z + q;
	e = z * 2;
	return c + z * q - e;
}
int main(){
	c = 1;
	d = a(c, 1);
	e = b(c, 4, c / 2);
	printf("%d", d);
	printf("%d", e);
}