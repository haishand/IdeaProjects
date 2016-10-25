/*
ID: cyrano63
LANG: c++
TASK: theme
 */
#include<stdio.h>
#include<iostream>
using namespace std;
int a[5005],f[5005][5005],i,j,n,ans;
int main()
{
  freopen("theme.in","r",stdin);
  freopen("theme.out","w",stdout);
  scanf("%ld",&n);
  for (i=1;i<=n;i++)
    scanf("%ld",&a[i]);
  for (i=1;i<=n;i++)
    for (j=i;j<=n;j++) 
    {
      if (a[i]-a[i-1]==a[j]-a[j-1]) 
        f[i][j]=max(f[i][j],f[i-1][j-1]+1);
      if (f[i][j]>ans) 
        ans=f[i][j];
    }
  if (ans<4) ans=-1;printf("%ld",ans+1);
  return 0;
}