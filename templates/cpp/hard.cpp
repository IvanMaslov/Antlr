#include<iostream>
int c;
int e;
int a(int x){
	int r;
	x = x * 2;
	r = x + 1;
	return r;
}
int b(int y){
	int c;
	y = y * 2;
	c = y + 2;
	return c;
}
int main(){
	c = 1;
	if (true || false){
		e = b(c) * a(4);
	}
	else{
		a(1);
	}
	printf("%d", e);
}