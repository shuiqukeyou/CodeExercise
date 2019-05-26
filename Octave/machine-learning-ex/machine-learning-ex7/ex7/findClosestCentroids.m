function idx = findClosestCentroids(X, centroids)
%FINDCLOSESTCENTROIDS computes the centroid memberships for every example
%   idx = FINDCLOSESTCENTROIDS (X, centroids) returns the closest centroids
%   in idx for a dataset X where each row is a single example. idx = m x 1 
%   vector of centroid assignments (i.e. each entry in range [1..K])
%

% Set K
K = size(centroids, 1);

% You need to return the following variables correctly.
idx = zeros(size(X,1), 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Go over every example, find its closest centroid, and store
%               the index inside idx at the appropriate location.
%               Concretely, idx(i) should contain the index of the centroid
%               closest to example i. Hence, it should be a value in the 
%               range 1..K
%
% Note: You can use a for-loop over the examples to compute this.
%
m = size(X,1);
for i = 1:m
  temp_x =  X(i,:); # ����ÿ��x
  l = -1; # ÿ�ֳ�ʼ��
  n = -1; # ÿ�ֳ�ʼ��
  for j = 1:K
    temp_1 = temp_x - centroids(j,:);
    temp_1 = temp_1 .^ 2 ;
    temp_1 = sum(temp_1); # ��������ƽ��
    if l == -1
      l = temp_1; # ��l��ֵû���޸Ĺ���ֱ���滻
      n = j;
    elseif l > temp_1
      l = temp_1; # ���l�ľ���ȵ�ǰ�ĵ�Ҫ�󣬱��浱ǰ����
      n = j;
    endif
  endfor
  idx(i) = n; # ������������
endfor





% =============================================================

end

