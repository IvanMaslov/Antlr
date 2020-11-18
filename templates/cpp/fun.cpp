#include<iostream>
int c;
int d;
int e;
int a(int x){
	int r;
	r = x + 1;
	return r;
}
int b(int y){
	int c;
	c = y + 2;
	return c;
}
int main(){
	c = 1;
	d = a(c);
	e = b(c);
	printf("%d", d);
	printf("%d", e);
}