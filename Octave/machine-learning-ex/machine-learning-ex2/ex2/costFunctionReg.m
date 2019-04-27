function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta
h = zeros(m,1);
z = X * theta; % 计算x的值

for i = 1:m
  h(i) = 1 / (1 + e^-z(i)); % 逻辑回归的方程h，，m行1列，对应当前θ下的m个输出
endfor

t = 0;
n = length(theta);
for i = 2:n % 计算正规项，不含theta0
  t = t + theta(i,:)^2;
endfor

J = -y' * log(h) - (1-y)' * log(1-h) + lambda/2*t;
J = J/m; % 成本计算完毕

theta0_temp = X'(1,:) * (h-y) /m; % 保存不需要正规化的theta0

grad = (X' * (h-y) + lambda * theta)/ m ;
grad(1) = theta0_temp; % 替换theta0



% =============================================================

end
